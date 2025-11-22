package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.graphics.Paint
import com.example.smarthomeshowerqueue.R
import com.example.smarthomeshowerqueue.presenter.LoginPresenter
import com.example.smarthomeshowerqueue.view.HomeActivity
import com.example.smarthomeshowerqueue.view.LoginView

class LoginModel : Activity(), LoginView {
    private lateinit var presenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        var usernameText = findViewById<EditText>(R.id.usernameText)
        var passwordText = findViewById<EditText>(R.id.passwordText)
        var loginBtn = findViewById<Button>(R.id.loginBtn)
        presenter = LoginPresenter(this)

        loginBtn.setOnClickListener{
            val user = usernameText.text.toString()
            val pass = passwordText.text.toString()
            presenter.onLoginClicked(user, pass)
        }

        val registerHere = findViewById<android.widget.TextView>(R.id.registerHere)
        registerHere.paintFlags = registerHere.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        registerHere.setOnClickListener {
            val intent = Intent(this, RegisterModel::class.java)
            startActivity(intent)
        }

        val forgotPassword = findViewById<android.widget.TextView>(R.id.forgotPassword)
        forgotPassword.paintFlags = forgotPassword.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordModel::class.java)
            startActivity(intent)
        }
    }

    override fun showLoginSuccess() {
        Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show()
    }

    override fun showPasswordTooShort() {
        Toast.makeText(this, "Password must be 6 characters", Toast.LENGTH_SHORT).show()
    }

    override fun showFieldsRequired() {
        Toast.makeText(this, "Please Fillout the Details needed.", Toast.LENGTH_SHORT).show()
    }

    override fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}