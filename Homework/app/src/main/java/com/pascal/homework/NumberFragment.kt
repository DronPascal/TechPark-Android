package com.pascal.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NumberFragment : Fragment() {
    private var number: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_number, container, false) as TextView
        if (arguments != null) {
            number = arguments!!.getInt("number")
        } else if (savedInstanceState != null) {
            number = savedInstanceState.getInt("number")
        }

        view.text = number.toString()
        view.setTextColor(
            resources.getColor(
                when {
                    number % 2 == 1 -> R.color.blue
                    else -> R.color.red
                }
            )
        )
        return view
    }
}
