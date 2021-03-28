package com.pascal.homework

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object {
        const val APP_SETTINGS_KEY = "prefNumberCount"
        const val APP_PREFERENCES_COUNTER = "prefNumberCount"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.main_frame) == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.main_frame,
                    NumbersListFragment().newInstance(getCounterFromPreferences())
                )
                .commit()
        }
    }

    private fun getCounterFromPreferences(): Int {
        val prefs: SharedPreferences =
            this.getSharedPreferences(APP_SETTINGS_KEY, Context.MODE_PRIVATE)
        return prefs.getInt(APP_PREFERENCES_COUNTER, NumbersListFragment.DEFAULT_NUM_COUNT)
    }

    fun saveCounterToPreferences(numbersCount: Int) {
        val prefs: SharedPreferences =
            this.getSharedPreferences(APP_SETTINGS_KEY, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putInt(APP_PREFERENCES_COUNTER, numbersCount).apply()
    }
}