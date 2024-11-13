package com.example.startingservice

import android.content.Intent
import android.os.Bundle
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

        editTextCountdown = findViewById(R.id.editTextCountdown)
        buttonStartCountdown = findViewById(R.id.buttonStartCountdown)
        buttonStartCountdown.setOnClickListener {
            val countdownValue = editTextCountdown.text.toString().toIntOrNull()
            if (countdownValue != null) {
                startCountdownService(countdownValue)
            } else {
                Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
private fun startCountdownService(countdownValue: Int) {

}

