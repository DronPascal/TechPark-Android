package com.pascal.homework

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NumbersListFragment : Fragment(), AdapterCallback {
    companion object {
        const val DEFAULT_NUM_COUNT = 100
        private const val APP_INSTANCE_COUNTER: String = "instNumberCount"
    }

    private var numbersCount: Int = DEFAULT_NUM_COUNT
    private lateinit var mRvNumbers: RecyclerView
    private lateinit var mAdapter: NumbersAdapter

    fun newInstance(count: Int): NumbersListFragment {
        val fragment = NumbersListFragment()
        fragment.numbersCount = count
        return fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_numbers_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null)
            numbersCount = savedInstanceState.getInt(APP_INSTANCE_COUNTER)
        mRvNumbers = view.findViewById(R.id.numbers_list)
        mAdapter = NumbersAdapter(this)

        mRvNumbers.adapter = mAdapter
        mRvNumbers.layoutManager = GridLayoutManager(
            context,
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> 3
                else -> 4
            }
        )
        view.findViewById<Button>(R.id.add_number_btn).setOnClickListener {
            mAdapter.addNumber()
            mRvNumbers.smoothScrollToPosition(numbersCount - 1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(APP_INSTANCE_COUNTER, numbersCount)
    }

    override fun onPause() {
        super.onPause()
        (activity as MainActivity).saveCounterToPreferences(numbersCount)

    }

    // AdapterCallback implementation
    override fun onItemSelected(num: Int, color: Int) {
        val bundle = Bundle()
        val numberFragment = NumberFragment()
        bundle.putInt(NumberFragment.NUMBER_KEY, num)
        bundle.putInt(NumberFragment.COLOR_KEY, color)
        numberFragment.arguments = bundle

        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, numberFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun getNumbersCount(): Int {
        return numbersCount
    }

    override fun increaseNumbersCount() {
        numbersCount++
    }

    override fun getColorFromContext(textColorId: Int): Int {
        if (context != null)
            return ContextCompat.getColor(context!!, textColorId)
        return NumberFragment.DEFAULT_СOLOR
    }
}

