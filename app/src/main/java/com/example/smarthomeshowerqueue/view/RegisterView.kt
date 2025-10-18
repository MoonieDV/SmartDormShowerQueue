package com.example.smarthomeshowerqueue.view

interface RegisterView {
    fun showRegisterSuccess()
    fun showPasswordTooShort()
    fun showFieldsRequired()
    fun navigateToLogin()
}