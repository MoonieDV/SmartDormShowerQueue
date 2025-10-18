package com.example.smarthomeshowerqueue.presenter

import com.example.smarthomeshowerqueue.model.Shower
import com.example.smarthomeshowerqueue.model.User
import com.example.smarthomeshowerqueue.view.HomeView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomePresenter(private val view: HomeView) {

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
    }

    fun onNotifyClicked() {
        view.notifyUser()
    }
}