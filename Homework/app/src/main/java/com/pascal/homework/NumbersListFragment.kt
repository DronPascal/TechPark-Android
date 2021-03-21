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

    private var initNumbersCount: Int = 100
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
        mRvNumbers = view.findViewById(R.id.numbers_list)
        mAdapter = NumbersAdapter()
        mAdapter.setParentFragment(this)
        mAdapter.setNumbersCount(count = initNumbersCount)

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
        outState.putInt("initNumbersCount", initNumbersCount)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        initNumbersCount = savedInstanceState?.getInt("initNumbersCount") ?: 100
        super.onViewStateRestored(savedInstanceState)
    }

    override fun onClick(view: View?) {
        if (view!!.id == R.id.add_number_btn) {
            var count: Int = mAdapter.addNumber()
            mRvNumbers.scrollToPosition(count - 1)
        } else if (view is TextView && activity != null) {
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
}
