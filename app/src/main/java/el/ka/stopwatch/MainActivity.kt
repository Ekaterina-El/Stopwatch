package el.ka.stopwatch

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import el.ka.stopwatch.fragments.AlarmClockFragment
import el.ka.stopwatch.fragments.StopwatchFragment
import el.ka.stopwatch.fragments.TimerFragment
import el.ka.stopwatch.util.MAIN_ACTIVITY
import el.ka.stopwatch.util.changeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val alarmClockFragment = AlarmClockFragment()
    private val timerFragment = TimerFragment()
    private val stopwatchFragment = StopwatchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MAIN_ACTIVITY = this
        changeFragment(stopwatchFragment)

        bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_alarm_clock -> changeFragment(alarmClockFragment);
                R.id.menu_timer -> changeFragment(timerFragment);
                R.id.menu_stopwatch -> changeFragment(stopwatchFragment);
            }
            true
        }
    }
}