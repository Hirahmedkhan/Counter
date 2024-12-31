package com.example.counter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counter.model.ModelValues
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CounterViewModel : ViewModel() {

    private val _counter = MutableStateFlow(ModelValues(0))
    val counter: StateFlow<ModelValues> get() = _counter

    var num = 0

    sealed class CounterEvent() {
        data object Increment : CounterEvent()
        data object Decrement : CounterEvent()
        data object Reset : CounterEvent()
    }

    fun onEvent(event: CounterEvent) {
        when (event) {
            is CounterEvent.Reset -> num == 0
            is CounterEvent.Increment -> if (num < 100) num++
            is CounterEvent.Decrement -> if (num > 0) num--
        }
        sendCounterUpdate()
    }

    private fun sendCounterUpdate() {
        viewModelScope.launch {
            _counter.emit(ModelValues(num))
        }
    }
}

