package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.smarthomeshowerqueue.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class ChangePasswordModel : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        val passwordLayout = findViewById<TextInputLayout>(R.id.passwordLayout)
        val confirmLayout = findViewById<TextInputLayout>(R.id.confirmLayout)
        val newPassword = findViewById<TextInputEditText>(R.id.newPassword)
        val confirmPassword = findViewById<TextInputEditText>(R.id.confirmPassword)
        val passwordError = findViewById<TextView>(R.id.passwordError)
        val confirmError = findViewById<TextView>(R.id.confirmError)
        val resetBtn = findViewById<MaterialButton>(R.id.resetPasswordBtn)

        fun clearErrors() {
            passwordLayout.error = null
            confirmLayout.error = null
            passwordError.visibility = android.view.View.GONE
            confirmError.visibility = android.view.View.GONE
        }

        resetBtn.setOnClickListener {
            clearErrors()
            val pass = newPassword.text?.toString() ?: ""
            val confirm = confirmPassword.text?.toString() ?: ""

            var hasError = false
            if (pass.length < 6) {
                passwordLayout.error = getString(R.string.error_password_short)
                passwordError.text = getString(R.string.error_password_short)
                passwordError.visibility = android.view.View.VISIBLE
                hasError = true
            }
            if (confirm.length < 6) {
                confirmLayout.error = getString(R.string.error_password_short)
                confirmError.text = getString(R.string.error_password_short)
                confirmError.visibility = android.view.View.VISIBLE
                hasError = true
            }
            if (!hasError && pass != confirm) {
                passwordLayout.error = getString(R.string.error_password_mismatch)
                confirmLayout.error = getString(R.string.error_password_mismatch)
                passwordError.text = getString(R.string.error_password_mismatch)
                confirmError.text = getString(R.string.error_password_mismatch)
                passwordError.visibility = android.view.View.VISIBLE
                confirmError.visibility = android.view.View.VISIBLE
                hasError = true
            }

            if (!hasError) {
                Toast.makeText(this, "Password reset successful", Toast.LENGTH_SHORT).show()
                // TODO: Hook actual reset logic (API) here
                finish()
            }
        }
    }
}
