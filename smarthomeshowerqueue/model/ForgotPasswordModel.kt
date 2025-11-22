package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.example.smarthomeshowerqueue.R

class ForgotPasswordModel : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val signInLink = findViewById<TextView>(R.id.signInLink)
        signInLink.setOnClickListener {
            val intent = Intent(this, LoginModel::class.java)
            startActivity(intent)
            finish()
        }

        // TODO: Hook up reset functionality when ready (send verification code)
    }
}
