package com.example.smarthomeshowerqueue.view


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.smarthomeshowerqueue.R
import com.example.smarthomeshowerqueue.model.Shower
import com.example.smarthomeshowerqueue.model.User
import com.example.smarthomeshowerqueue.presenter.HomePresenter

class HomeActivity : AppCompatActivity(), HomeView {

    private lateinit var showerGrid: GridLayout
    private lateinit var queueList: LinearLayout
    private lateinit var updatedTime: TextView
    private lateinit var presenter: HomePresenter
    private lateinit var notifyButton: Button
    private lateinit var floorSpinner: Spinner
    private lateinit var bottomNav: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        showerGrid = findViewById(R.id.showerGrid)
        queueList = findViewById(R.id.queueList)
        updatedTime = findViewById(R.id.updatedTime)
        notifyButton = findViewById(R.id.notifyButton)
        bottomNav = findViewById(R.id.bottomNavigation)
        val contentScroll = findViewById<android.widget.ScrollView>(R.id.contentScroll)

        presenter = HomePresenter(this)
        presenter.loadData()

        notifyButton.setOnClickListener {
            presenter.onNotifyClicked()
        }

        floorSpinner = findViewById(R.id.floorSpinner)

        // Bottom navigation wiring
        bottomNav.selectedItemId = R.id.nav_home
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // already here
                R.id.nav_menu -> {
                    Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }

        // Apply window insets so content scrolls above system navigation bar and bottom nav
        ViewCompat.setOnApplyWindowInsetsListener(contentScroll) { v, insets ->
            val bottomBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
            ).bottom
            val navHeight = resources.getDimensionPixelSize(R.dimen.bottom_nav_height)
            v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, bottomBars + navHeight)
            insets
        }

// Sample floor list - replace with your actual floors if needed
        val floors = listOf("Floor 1", "Floor 2", "Floor 3")

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            floors
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        floorSpinner.adapter = adapter

        // Optional: handle floor selection changes
        floorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedFloor = floors[position]
                // Notify presenter or update UI accordingly
                Toast.makeText(this@HomeActivity, "Selected: $selectedFloor", Toast.LENGTH_SHORT).show()
                // e.g. presenter.loadShowersForFloor(selectedFloor)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

    }



    override fun showShowers(showers: List<Shower>) {
        showerGrid.removeAllViews()
        for (shower in showers) {
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = android.view.Gravity.CENTER
                setPadding(0, 0, 0, 0)
            }

            val lp = GridLayout.LayoutParams().apply {
                width = GridLayout.LayoutParams.WRAP_CONTENT
                height = GridLayout.LayoutParams.WRAP_CONTENT
                setMargins(16, 12, 16, 12)
                setGravity(android.view.Gravity.CENTER)
            }
            layout.layoutParams = lp

            val status = TextView(this).apply {
                text = if (shower.isOccupied) "Occupied" else "Available"
                textSize = 12f
                setTextColor(ContextCompat.getColor(this@HomeActivity, android.R.color.darker_gray))
            }

            val size = (72 * resources.displayMetrics.density).toInt()
            val frameLayout = FrameLayout(this).apply {
                layoutParams = LinearLayout.LayoutParams(size, size)
                background = if (shower.isOccupied) {
                    ContextCompat.getDrawable(this@HomeActivity, R.drawable.occupied_border)
                } else {
                    ContextCompat.getDrawable(this@HomeActivity, R.drawable.available_border)
                }
            }

            val image = ImageView(this).apply {
                setImageResource(R.drawable.shower_icon)
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }

            frameLayout.addView(image)

            val label = TextView(this).apply {
                text = shower.id
                textSize = 12f
            }

            layout.addView(status)
            layout.addView(frameLayout)
            layout.addView(label)
            showerGrid.addView(layout)
        }
    }

    override fun showQueue(users: List<User>) {
        // Clear and intentionally leave empty for now per design request
        queueList.removeAllViews()
    }

    override fun updateTime(time: String) {
        updatedTime.text = time
    }

    override fun notifyUser() {
        Toast.makeText(this, "You will be notified", Toast.LENGTH_SHORT).show()
    }
}