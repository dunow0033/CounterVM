package com.example.countervm

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {

    private var _count: MutableLiveData<Int> = MutableLiveData(0)
    val count: LiveData<Int> = _count

    private var counter: Int = 0

    fun incrementCount() {
        counter++
        _count.value = counter
    }

    fun resetCount() {
        counter = 0
        _count.value = counter
    }
}