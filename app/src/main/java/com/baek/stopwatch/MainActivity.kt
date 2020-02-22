package com.baek.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var timeTask: Timer? = null
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabPlayPause.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }
    }

    private fun start() {
        fabPlayPause.setImageResource(R.drawable.ic_pause_black_24dp)

        timeTask = timer(period = 10) {
            time++
            val sec = time / 100
            val milli = time % 100

            runOnUiThread {
                tvSec.text = "$sec"
                tvMilliSec.text = "$milli"
            }
        }
    }

    private fun pause() {
        fabPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp)

        timeTask?.cancel()
    }
}
