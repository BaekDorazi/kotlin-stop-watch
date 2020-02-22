package com.baek.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.concurrent.timer
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {
    private var time = 0
    private var timeTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fabReset.setOnClickListener {
            reset()
        }

        fabPlayPause.setOnClickListener {
            isRunning = !isRunning

            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        btnLab.setOnClickListener {
            recordLapTime()
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

    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAB: ${lapTime / 100}.${lapTime % 100}"

        lapLayout.addView(textView, 0)
        lap++
    }

    private fun reset() {
        timeTask?.cancel()

        time = 0
        isRunning = false
        fabPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp)
        tvSec.text = "0"
        tvMilliSec.text = "00"

        lapLayout.removeAllViews()
        lap = 1
    }
}
