package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.smarthomeshowerqueue.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class PrivacyPolicyModel : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, com.example.smarthomeshowerqueue.view.HomeActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> true
            }
        }
    }
}
