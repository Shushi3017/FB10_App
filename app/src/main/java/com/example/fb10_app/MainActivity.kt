package com.example.fb10_app

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.widget.Toolbar
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.view.Menu
import android.view.MenuItem
import com.example.fb10_app.databinding.ActivityMainBinding
import com.google.android.material.search.SearchView
import com.example.fb10_app.users.UserListActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        val prefs = getSharedPreferences("ThemePrefs", MODE_PRIVATE)

        if (prefs.getBoolean("dark_mode", false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
     binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.title = prefs.getString("toolbar_title", "Home")


        if (prefs.getBoolean("dark_mode", false)) {
            toolbar.setBackgroundColor(Color.parseColor("#1E293B"))

        } else {
            toolbar.setBackgroundColor(Color.parseColor("#2E7D32"))

        }

        val themeSwitch = binding.themeSwitch
        themeSwitch.isChecked = prefs.getBoolean("dark_mode", false)
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->

            prefs.edit().putBoolean("dark_mode", isChecked).apply()
            prefs.edit().putString("toolbar_title", toolbar.title.toString()).apply()

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }



val drawerLayout = binding.drawerLayout
        val navigationView = binding.navigationView
        val bottomNav = binding.bottomNavigationView


        toolbar.setNavigationOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        navigationView.setNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.nav_notification -> {
                    Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show()
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }



        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        bottomNav.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, HomeFragment())
                        .commit()
                    toolbar.title = "Home"
                    true
                }

                R.id.about -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AboutFragment())
                        .commit()
                    toolbar.title = "About"
                    true
                }

                R.id.account -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AccountFragment())
                        .commit()
                    toolbar.title = "Account"
                    true
                }

                else -> false
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//variables

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.topnav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_users -> {
                Toast.makeText(this, "Users clicked", Toast.LENGTH_LONG).show()

                startActivity(Intent(this, UserListActivity::class.java))

                true
            }

            R.id.action_logout -> {
                val intent = Intent(this, RegistrationPage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.exit -> {
                finishAffinity()
                true
            }

            else -> super.onOptionsItemSelected(item)


        }
    }
}
