package com.example.bestprovince

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val compareButton: Button = findViewById(R.id.compareButton)

        compareButton.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java)

            // Ontario
            val ontarioWeather = findViewById<Spinner>(R.id.ontarioWeather).selectedItem.toString()
            val ontarioCostOfLiving = findViewById<EditText>(R.id.ontarioCostOfLiving).text.toString().toDoubleOrNull() ?: 0.0
            val ontarioSafety = findViewById<Spinner>(R.id.ontarioSafety).selectedItem.toString()

            // British Columbia
            val bcWeather = findViewById<Spinner>(R.id.bcWeather).selectedItem.toString()
            val bcCostOfLiving = findViewById<EditText>(R.id.bcCostOfLiving).text.toString().toDoubleOrNull() ?: 0.0
            val bcSafety = findViewById<Spinner>(R.id.bcSafety).selectedItem.toString()

            // Alberta
            val albertaWeather = findViewById<Spinner>(R.id.albertaWeather).selectedItem.toString()
            val albertaCostOfLiving = findViewById<EditText>(R.id.albertaCostOfLiving).text.toString().toDoubleOrNull() ?: 0.0
            val albertaSafety = findViewById<Spinner>(R.id.albertaSafety).selectedItem.toString()

            // Nova Scotia
            val nsWeather = findViewById<Spinner>(R.id.nsWeather).selectedItem.toString()
            val nsCostOfLiving = findViewById<EditText>(R.id.nsCostOfLiving).text.toString().toDoubleOrNull() ?: 0.0
            val nsSafety = findViewById<Spinner>(R.id.nsSafety).selectedItem.toString()

            intent.putExtra("ontarioWeather", ontarioWeather)
            intent.putExtra("ontarioCostOfLiving", ontarioCostOfLiving)
            intent.putExtra("ontarioSafety", ontarioSafety)
            intent.putExtra("bcWeather", bcWeather)
            intent.putExtra("bcCostOfLiving", bcCostOfLiving)
            intent.putExtra("bcSafety", bcSafety)
            intent.putExtra("albertaWeather", albertaWeather)
            intent.putExtra("albertaCostOfLiving", albertaCostOfLiving)
            intent.putExtra("albertaSafety", albertaSafety)
            intent.putExtra("nsWeather", nsWeather)
            intent.putExtra("nsCostOfLiving", nsCostOfLiving)
            intent.putExtra("nsSafety", nsSafety)

            startActivity(intent)
        }
    }
}
