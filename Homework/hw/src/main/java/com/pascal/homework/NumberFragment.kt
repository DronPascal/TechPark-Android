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
        number = if (arguments != null) {
            arguments!!.getInt("number")
        } else savedInstanceState?.getInt("number") ?: 0

        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView = view.findViewById<TextView>(R.id.number_text)
        textView.text = number.toString()
        textView.setTextColor(
            resources.getColor(
                when {
                    number % 2 == 1 -> R.color.blue
                    else -> R.color.red
                }
            )
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("number", number)
        super.onSaveInstanceState(outState)
    }
}
