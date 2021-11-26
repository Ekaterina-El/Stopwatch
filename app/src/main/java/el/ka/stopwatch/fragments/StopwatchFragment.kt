package el.ka.stopwatch.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import el.ka.stopwatch.R
import el.ka.stopwatch.util.StopwatchState
import kotlinx.android.synthetic.main.fragment_stopwatch.*
import kotlin.properties.Delegates

class StopwatchFragment : Fragment(R.layout.fragment_stopwatch) {
    private var sec by Delegates.notNull<Int>()
    private lateinit var isRunning: StopwatchState
    private lateinit var handler: Handler

    override fun onAttach(context: Context) {
        super.onAttach(context)
        handler = Handler()
        sec = 0;
        isRunning = StopwatchState.STOPPED
        startTimer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateTimer()
        stopwatch_btn_start.setOnClickListener {
            isRunning = StopwatchState.WORKING
            updateTimer()
        }

        stopwatch_btn_pause.setOnClickListener {
            isRunning = StopwatchState.invertState(isRunning);
            updateTimer()
        }

        stopwatch_btn_stop.setOnClickListener {
            isRunning = StopwatchState.STOPPED
            sec = 0
            updateTimer()
        }
    }

    private fun startTimer() {
        handler.post(object : Runnable {
            override fun run() {
                updateTimer()
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun updateTimer() {
        val hrs = sec.div(3600)
        val min = (sec.mod(3600)).div(60)
        val secs = sec.mod(60)

        val timeT = String.format("%d:%02d:%02d", hrs, min, secs)

        when (isRunning) {
            StopwatchState.WORKING -> {
                stopwatch_btn_stop?.visibility = View.VISIBLE
                stopwatch_btn_pause?.visibility = View.VISIBLE
                stopwatch_btn_start?.visibility = View.GONE
                sec += 1
                stopwatch_btn_pause?.text =
                    if (isRunning == StopwatchState.WORKING) getString(R.string.pause) else getString(R.string.start)
            }

            StopwatchState.PAUSE -> {

                stopwatch_btn_stop?.visibility = View.VISIBLE
                stopwatch_btn_pause?.visibility = View.VISIBLE
                stopwatch_btn_start?.visibility = View.GONE

                stopwatch_btn_pause?.text =
                    if (isRunning == StopwatchState.WORKING) getString(R.string.pause) else getString(R.string.start)
            }

            StopwatchState.STOPPED -> {
                stopwatch_btn_stop?.visibility = View.GONE
                stopwatch_btn_pause?.visibility = View.GONE
                stopwatch_btn_start?.visibility = View.VISIBLE
            }
        }
        stopwatch_tv_time_value?.text = timeT
    }
}