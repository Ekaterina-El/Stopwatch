package el.ka.stopwatch.util

import androidx.fragment.app.Fragment
import el.ka.stopwatch.R

fun changeFragment(fragment: Fragment) {
    MAIN_ACTIVITY.supportFragmentManager.beginTransaction().apply {
        this.replace(R.id.main_fl, fragment)
        addToBackStack(fragment.javaClass.simpleName.toString())
        commit()
    }
}