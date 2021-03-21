package com.pascal.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.main_frame) == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, NumbersListFragment())
                .commit()
        }
    }
}