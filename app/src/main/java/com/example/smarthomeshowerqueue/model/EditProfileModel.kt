package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.example.smarthomeshowerqueue.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout

class EditProfileModel : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val email = findViewById<TextInputEditText>(R.id.email)
        val username = findViewById<TextInputEditText>(R.id.usernameText)
        val fullName = findViewById<TextInputEditText>(R.id.fullName)

        findViewById<MaterialButton>(R.id.saveBtn).setOnClickListener {
            // TODO: persist changes to storage/backend
            Toast.makeText(this, "Profile saved", Toast.LENGTH_SHORT).show()
            finish()
        }

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
                R.id.nav_menu -> {
                    showRightSideMenu(bottomNav)
                    true
                }
                else -> true
            }
        }
    }

    private fun showRightSideMenu(bottomNav: BottomNavigationView) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_side_menu)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))

        val window = dialog.window
        if (window != null) {
            val params = WindowManager.LayoutParams()
            params.copyFrom(window.attributes)
            params.width = (resources.displayMetrics.widthPixels * 0.8).toInt()
            params.height = WindowManager.LayoutParams.MATCH_PARENT
            params.gravity = Gravity.END
            window.attributes = params
            window.attributes.windowAnimations = R.style.SideMenuDialogAnimation
        }

        dialog.findViewById<ImageView>(R.id.closeBtn)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
        }
        dialog.findViewById<LinearLayout>(R.id.itemPrivacy)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            startActivity(Intent(this, PrivacySecurityModel::class.java))
            finish()
        }
        dialog.findViewById<LinearLayout>(R.id.itemPrivacyPolicy)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            startActivity(Intent(this, PrivacyPolicyModel::class.java))
            finish()
        }
        dialog.findViewById<LinearLayout>(R.id.itemTerms)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            startActivity(Intent(this, TermsConditionsModel::class.java))
            finish()
        }
        dialog.findViewById<LinearLayout>(R.id.profile)?.setOnClickListener {
            // Navigate to profile from edit
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            startActivity(Intent(this, ProfileModel::class.java))
            finish()
        }
        dialog.findViewById<LinearLayout>(R.id.itemLogout)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            val intent = Intent(this, LoginModel::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
        dialog.setOnDismissListener { bottomNav.selectedItemId = R.id.nav_home }
        dialog.show()
    }
}
