package com.example.smarthomeshowerqueue.view

interface LoginView {
    fun showLoginSuccess()
    fun showPasswordTooShort()
    fun showFieldsRequired()
    fun navigateToHome()
}