package com.pascal.homework

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NumbersListFragment : Fragment(), View.OnClickListener {

    private var numbersCount: Int = 100
    private lateinit var mRvNumbers: RecyclerView
    private lateinit var mAdapter: NumbersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        numbersCount = savedInstanceState?.getInt("numbersCount") ?: 100

        mRvNumbers = view.findViewById(R.id.numbers_list)
        mAdapter = NumbersAdapter()

        mRvNumbers.adapter = mAdapter
        mRvNumbers.layoutManager = GridLayoutManager(
            context,
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> 3
                else -> 4
            }
        )
        view.findViewById<Button>(R.id.add_number_btn).setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("numbersCount", numbersCount)
        super.onSaveInstanceState(outState)
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.add_number_btn) {
            var count: Int = mAdapter.addNumber()
            mRvNumbers.scrollToPosition(count - 1)
        } else if (view is TextView) {
            val bundle = Bundle()
            bundle.putInt("number", view.text.toString().toInt())

            val numberFragment = NumberFragment()
            numberFragment.setArguments(bundle)

            activity!!.supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, numberFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    // Adapter
    private inner class NumbersAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            holder.itemView.setOnClickListener(this@NumbersListFragment)
        }

        override fun getItemCount(): Int {
            return numbersCount
        }

        fun addNumber(): Int {
            numbersCount++
            notifyItemInserted(numbersCount - 1)
            return numbersCount
        }

        inner class NumbersViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {}
    }
}
