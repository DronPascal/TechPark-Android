package com.pascal.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class NumbersAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var numbersCount: Int = 100
    private lateinit var parentFragment: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val numberView = layoutInflater.inflate(R.layout.cell_number, parent, false)
        return NumbersViewHolder(itemView = numberView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var mTxtNumber: TextView = holder.itemView.findViewById(R.id.number_text)

        mTxtNumber.text = (position + 1).toString()
        mTxtNumber.setTextColor(
            holder.itemView.resources.getColor(
                when {
                    position % 2 == 1 -> R.color.blue
                    else -> R.color.red
                }
            )
        )
        holder.itemView.setOnClickListener(parentFragment)
    }

    override fun getItemCount(): Int {
        return numbersCount
    }

    fun setNumbersCount(count: Int) {
        numbersCount = count
    }

    fun setParentFragment(fragment: View.OnClickListener) {
        parentFragment = fragment
    }

    fun addNumber(): Int {
        numbersCount++
        notifyDataSetChanged()
        return numbersCount
    }

    class NumbersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }
}