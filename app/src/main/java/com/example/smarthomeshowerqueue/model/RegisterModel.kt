package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.smarthomeshowerqueue.R
import com.example.smarthomeshowerqueue.presenter.RegisterPresenter
import com.example.smarthomeshowerqueue.view.RegisterView

class RegisterModel : Activity(), RegisterView {
    private lateinit var presenter: RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        var usernameText = findViewById<EditText>(R.id.usernameText)
        var passwordText = findViewById<EditText>(R.id.passwordText)
        var loginBtn = findViewById<Button>(R.id.registerBtn)
        presenter = RegisterPresenter(this)

        val signInLink = findViewById<android.widget.TextView>(R.id.signInLink)
        signInLink.setOnClickListener {
            val intent = Intent(this, LoginModel::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener{
            val user = usernameText.text.toString()
            val pass = passwordText.text.toString()
            presenter.onRegisterClicked(user, pass)
        }
    }

    override fun showRegisterSuccess() {
        Toast.makeText(this, "Registered Successfully!", Toast.LENGTH_SHORT).show()
    }

    override fun showPasswordTooShort() {
        Toast.makeText(this, "Password must be 6 characters", Toast.LENGTH_SHORT).show()
    }

    override fun showFieldsRequired() {
        Toast.makeText(this, "Please Fillout the Details needed.", Toast.LENGTH_SHORT).show()
    }

    override fun navigateToLogin() {
        val intent = Intent(this, LoginModel::class.java)
        startActivity(intent)
    }
}