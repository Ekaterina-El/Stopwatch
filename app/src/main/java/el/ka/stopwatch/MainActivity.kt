package el.ka.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var sec = 0
    var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startTimer()

        btn_start.setOnClickListener {
            isRunning = true
            it.visibility = View.GONE
            btn_pause.visibility = View.VISIBLE
            btn_stop.visibility = View.VISIBLE
        }

        btn_pause.setOnClickListener {
            isRunning = !isRunning
            sec -= 1
            btn_pause.text = if (isRunning) getString(R.string.pause) else getString(R.string.start)
        }

        btn_stop.setOnClickListener {
            isRunning = false
            sec = 0

            btn_stop.visibility = View.GONE
            btn_pause.visibility = View.GONE
            btn_start.visibility = View.VISIBLE
        }
    }

    private fun startTimer() {
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hrs = sec.div(3600)
                val min = (sec.mod(3600)).div(60)
                val secs = sec.mod(60)

                val timeT = String.format("%d:%02d:%02d", hrs, min, secs)

                if (isRunning) {
                    sec += 1
                }

                tv_time_value.text = timeT

                handler.postDelayed(this, 1000)
            }

        })
    }
}