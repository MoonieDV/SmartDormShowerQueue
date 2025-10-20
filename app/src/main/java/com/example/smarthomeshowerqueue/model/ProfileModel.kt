package com.example.smarthomeshowerqueue.model

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.smarthomeshowerqueue.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout

class ProfileModel : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        findViewById<MaterialButton>(R.id.editProfileBtn).setOnClickListener {
            startActivity(Intent(this, EditProfileModel::class.java))
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
        }
        dialog.findViewById<LinearLayout>(R.id.itemPrivacyPolicy)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            startActivity(Intent(this, PrivacyPolicyModel::class.java))
        }
        dialog.findViewById<LinearLayout>(R.id.itemTerms)?.setOnClickListener {
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
            startActivity(Intent(this, TermsConditionsModel::class.java))
        }
        dialog.findViewById<LinearLayout>(R.id.profile)?.setOnClickListener {
            // Already on profile; just close and reset highlight
            dialog.dismiss()
            bottomNav.selectedItemId = R.id.nav_home
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
