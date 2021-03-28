package com.pascal.homework

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NumbersAdapter(private val mAdapterCallback: AdapterCallback) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        private const val ODD_COLOR = R.color.blue
        private const val EVEN_COLOR = R.color.red
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val numberView = layoutInflater.inflate(R.layout.cell_number, parent, false)
        return NumbersViewHolder(itemView = numberView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val mTxtNumber: TextView = holder.itemView.findViewById(R.id.number_text)

        val curNumber: Int = position + 1
        mTxtNumber.text = (curNumber).toString()

        val textColorId: Int = when {
            curNumber % 2 == 1 -> ODD_COLOR
            else -> EVEN_COLOR
        }
        val textColor = mAdapterCallback.getColorFromContext(textColorId)
        mTxtNumber.setTextColor(textColor)

        holder.itemView.setOnClickListener {
            mAdapterCallback.onItemSelected(curNumber, textColor)
        }
    }

    override fun getItemCount(): Int {
        return mAdapterCallback.getNumbersCount()
    }

    fun addNumber() {
        mAdapterCallback.increaseNumbersCount()
        notifyItemInserted(mAdapterCallback.getNumbersCount() - 1)
    }

    inner class NumbersViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {}
}
