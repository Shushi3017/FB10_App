package com.example.campusconnect

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.campusconnect.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Create binding object
    private lateinit var binding: ActivityMainBinding

    //Objects for notification
    private val channelId = "demo_channel"
    private var notificationId = 1

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) {
            granted -> if(granted) sendNotification()
            else Toast.makeText(this, "Notification permission not granted", Toast.LENGTH_LONG)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the binding object
        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)

        // Set the toolbar as the action bar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = "Campus Connect"

        binding.bottomNav.setOnItemSelectedListener {
            // 1. Initialize fragments to be loaded
            item ->
                val fragment = when(item.itemId) {
                    R.id.btn_home -> HomeFragment()
                    R.id.btn_dashboard -> DashboardFragment()
                    R.id.btn_notification -> NotificationFragment()
                    else -> HomeFragment()
                }
            // 2. Create fragment support
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit()

            true
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Set the default fragment when app loads
        if(savedInstanceState == null)
            binding.bottomNav.selectedItemId = R.id.btn_home
    }

    // Inflate the toolbar with the main menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Use the menu inflater to inflate the menu
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.mn_settings -> {
                Toast.makeText(this, "Settings menu is selected", Toast.LENGTH_LONG).show()
                true
            }
            R.id.mn_about -> {
                Toast.makeText(this, "About menu is selected", Toast.LENGTH_LONG).show()
                true
            }
            R.id.mn_notify -> {
                checkPermissionAndNotify()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    // Create Notification Helper Function
    private fun createNotificationChannel() {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(channelId,
                "Demo Channel",
                NotificationManager.IMPORTANCE_DEFAULT)
                .apply {
                    description = "This is a demo notification"
                }
            getSystemService(NotificationManager::class.java)
                .createNotificationChannel(channel)
        }
    }

    // Helper Function to check if permission is granted
    private fun checkPermissionAndNotify () {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(
                Manifest.permission.POST_NOTIFICATIONS
            )
        }else {
            sendNotification()
        }
    }

    //Send the notification
    private fun sendNotification() {
        //Create a notification object
        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Campus Connect")
            .setContentText("happi happi haappi")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        getSystemService(
            NotificationManager::class.java
        ).notify(notificationId++, notification)

        // sabi ni sir add niyo daw para lumabas yung nasa notif daw
        // ↓
        // createNotificationChannel()

    }
}