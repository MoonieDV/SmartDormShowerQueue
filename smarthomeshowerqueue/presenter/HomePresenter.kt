package com.example.smarthomeshowerqueue.presenter

import com.example.smarthomeshowerqueue.model.HistoryLog
import com.example.smarthomeshowerqueue.model.Shower
import com.example.smarthomeshowerqueue.model.User
import com.example.smarthomeshowerqueue.view.HomeView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomePresenter(private val view: HomeView) {

    // Use the Firebase URL from google-services.json
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance(
        "https://smart-dorm-shower-queue-default-rtdb.asia-southeast1.firebasedatabase.app"
    )
    private val logsReference: DatabaseReference = database.getReference("bathroom/logs")

    fun loadData() {
        val showers = listOf(
            Shower("B1", true),
            Shower("B2", false),
            Shower("B3", false),
            Shower("B4", false),
            Shower("B5", false),
            Shower("B6", false)
        )
        val queue = listOf(
            User("Julia Roberts"),
            User("Juli Roberts")
        )

        view.showShowers(showers)
        view.showQueue(queue)

        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        view.updateTime("Updated: ${sdf.format(Date())}")
        
        // Load history logs from Firebase
        loadHistoryLogs()
    }

    private fun loadHistoryLogs() {
        logsReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val logs = mutableListOf<HistoryLog>()
                
                snapshot.children.forEach { child ->
                    val status = child.child("status").getValue(String::class.java) ?: ""
                    val timestamp = child.child("timestamp").getValue(String::class.java) ?: ""
                    val key = child.key ?: ""
                    
                    // Extract date from timestamp if possible, or use current date
                    val date = extractDateFromTimestamp(timestamp)
                    
                    logs.add(HistoryLog(
                        key = key,
                        status = status,
                        timestamp = timestamp,
                        date = date
                    ))
                }
                
                // Sort by timestamp (most recent first)
                val sortedLogs = logs.sortedByDescending { it.timestamp }
                
                view.showHistoryLogs(sortedLogs)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error - could show a message to user
            }
        })
    }
    
    private fun extractDateFromTimestamp(timestamp: String): String {
        // If timestamp format includes date, extract it
        // For now, we'll use the current date or format as needed
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    fun onNotifyClicked() {
        view.notifyUser()
    }
}