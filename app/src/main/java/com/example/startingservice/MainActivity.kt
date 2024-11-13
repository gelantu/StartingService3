package com.example.startingservice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCountdown: EditText
    private lateinit var buttonStartCountdown: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        editTextCountdown = findViewById(R.id.editTextCountdown)
        buttonStartCountdown = findViewById(R.id.buttonStartCountdown)
        buttonStartCountdown.setOnClickListener {
            val countdownValue = editTextCountdown.text.toString().toIntOrNull()
            if (countdownValue != null) {
                Log.d("MainActivity", "Starting countdown service with value: $countdownValue")
                startCountdownService(countdownValue)
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun startCountdownService(countdownValue: Int) {
        val intent = Intent(this, CountdownService::class.java).apply {
            putExtra("COUNTDOWN_VALUE", countdownValue)
        }
        startService(intent)
    }
}


