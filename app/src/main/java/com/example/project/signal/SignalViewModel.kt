package com.example.project.signal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.entities.Signal
import com.example.project.data.repositories.SignalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignalViewModel @Inject constructor(private val signalRepository: SignalRepository)
    : ViewModel() {

    val signals = signalRepository.getAllSignals()

    fun deleteSignal(signal: Signal) {
        viewModelScope.launch {
            signalRepository.deleteSignal(signal)
        }
    }



}