package com.example.startingservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountdownService : Service() {
    private val serviceScope = CoroutineScope(Dispatchers.Default + Job())

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val countdownValue = intent?.getIntExtra("COUNTDOWN_VALUE", 0) ?: 0
        startCountdown(countdownValue)
        return START_NOT_STICKY
    }

    private fun startCountdown(countdownValue: Int) {
        serviceScope.launch {
            for (i in countdownValue downTo 0) {
                Log.d("CountdownService", "Countdown: $i")
                delay(1000) // 1 second delay
            }
            Log.d("CountdownService", "Countdown finished")
            stopSelf()
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }
}