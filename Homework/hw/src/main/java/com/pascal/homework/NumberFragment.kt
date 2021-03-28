package com.pascal.homework

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class NumberFragment : Fragment() {
    companion object {
        const val NUMBER_KEY = "number"
        const val COLOR_KEY = "color"
        const val DEFAULT_NUMBER = 0
        const val DEFAULT_СOLOR = Color.BLACK
    }

    private var number: Int = DEFAULT_NUMBER
    private var color: Int = DEFAULT_СOLOR

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let { number = it.getInt(NUMBER_KEY) }
        arguments?.let { color = it.getInt(COLOR_KEY) }
        return inflater.inflate(R.layout.fragment_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val textView = view.findViewById<TextView>(R.id.number_text)
        textView.text = number.toString()
        textView.setTextColor(color)
    }
}


