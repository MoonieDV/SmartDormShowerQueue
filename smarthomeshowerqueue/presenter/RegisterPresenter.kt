package com.example.smarthomeshowerqueue.presenter

import com.example.smarthomeshowerqueue.view.RegisterView

class RegisterPresenter(private val view: RegisterView) {
    fun onRegisterClicked(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            view.showFieldsRequired()
            return
        }
        if (password.length < 6) {
            view.showPasswordTooShort()
            return
        }
        view.showRegisterSuccess()
        view.navigateToLogin()
    }
}