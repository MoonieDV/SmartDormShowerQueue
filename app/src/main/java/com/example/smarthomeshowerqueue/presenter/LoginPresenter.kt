package com.example.smarthomeshowerqueue.presenter

import com.example.smarthomeshowerqueue.view.LoginView

class LoginPresenter(private val view: LoginView) {
    fun onLoginClicked(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            view.showFieldsRequired()
            return
        }
        if (password.length < 6) {
            view.showPasswordTooShort()
            return
        }
        view.showLoginSuccess()
        view.navigateToHome()
    }
}