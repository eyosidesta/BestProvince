package com.example.bestprovince

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ResultActivity : AppCompatActivity() {
    private val CHANNEL_ID = "ProvinceComparisonChannel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Receive data from the intent
        val ontarioWeather = intent.getStringExtra("ontarioWeather")
        val ontarioCostOfLiving = intent.getDoubleExtra("ontarioCostOfLiving", 0.0)
        val ontarioSafety = intent.getStringExtra("ontarioSafety")

        val bcWeather = intent.getStringExtra("bcWeather")
        val bcCostOfLiving = intent.getDoubleExtra("bcCostOfLiving", 0.0)
        val bcSafety = intent.getStringExtra("bcSafety")

        val albertaWeather = intent.getStringExtra("albertaWeather")
        val albertaCostOfLiving = intent.getDoubleExtra("albertaCostOfLiving", 0.0)
        val albertaSafety = intent.getStringExtra("albertaSafety")

        val nsWeather = intent.getStringExtra("nsWeather")
        val nsCostOfLiving = intent.getDoubleExtra("nsCostOfLiving", 0.0)
        val nsSafety = intent.getStringExtra("nsSafety")

        // Determine the best province based on the least cost of living
        val provinceCosts = mapOf(
            "Ontario" to ontarioCostOfLiving,
            "British Columbia" to bcCostOfLiving,
            "Alberta" to albertaCostOfLiving,
            "Nova Scotia" to nsCostOfLiving
        )
        val bestProvince = provinceCosts.minByOrNull { it.value }?.key ?: "Unknown"

        val bestWeather = when (bestProvince) {
            "Ontario" -> ontarioWeather
            "British Columbia" -> bcWeather
            "Alberta" -> albertaWeather
            "Nova Scotia" -> nsWeather
            else -> "Unknown"
        }

        val bestSafety = when (bestProvince) {
            "Ontario" -> ontarioSafety
            "British Columbia" -> bcSafety
            "Alberta" -> albertaSafety
            "Nova Scotia" -> nsSafety
            else -> "Unknown"
        }

        // Display the results in TextView
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        resultTextView.text = "$bestProvince is the best province to live in.\n\n" +
                "Weather: $bestWeather\n" +
                "Cost of Living: ${provinceCosts[bestProvince]} CAD\n" +
                "Safety: $bestSafety"

        // Create a notification showing the best province
        createNotificationChannel()

        val notificationIntent = Intent(this, AfterNotificationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Make sure you have a proper icon
            .setContentTitle("Best Province to Live In")
            .setContentText("$bestProvince is the best province to live in")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setLights(0xFF00FF00.toInt(), 1000, 1000) // Example light configuration
            .setVibrate(longArrayOf(0, 1000, 500, 1000))

        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Province Comparison"
            val descriptionText = "Channel for best province notification"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

