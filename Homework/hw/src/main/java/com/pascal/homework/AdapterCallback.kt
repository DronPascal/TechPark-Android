package com.pascal.homework

interface AdapterCallback {
    fun onItemSelected(num: Int, color: Int)
    fun getNumbersCount(): Int
    fun increaseNumbersCount()
    fun getColorFromContext(textColorId: Int): Int
}