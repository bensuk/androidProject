package com.example.project.signal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.entities.Signal
import com.example.project.data.repositories.SignalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignalUpdateViewModel @Inject constructor
    (private val signalRepository: SignalRepository): ViewModel() {

    var signalState by mutableStateOf(
        Signal(0, "", Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE))
        private set

    private val _validationState = mutableStateListOf<Field>()
    val validationState: List<Field> = _validationState

    fun getSignal(id: Int) {
        viewModelScope.launch {
            signalState = signalRepository.getSignal(id).first()
        }
    }

    fun updateSignalState(signal: String, field: Field) {
        when (field) {
            Field.Signal1 -> {
                if (signal.isEmpty()) {
                    signalState = signalState.copy(sensor1 = Int.MIN_VALUE)
                }
                else if (signal.all { it.isDigit()} && signal.length < 4) {
                    signalState = signalState.copy(sensor1 = signal.toInt())

                    if (validationState.contains(Field.Signal1))
                        _validationState.remove(Field.Signal1)
                }
            }

            Field.Signal2 -> {
                if (signal.isEmpty()) {
                    signalState = signalState.copy(sensor2 = Int.MIN_VALUE)
                }
                else if (signal.all { it.isDigit() } && signal.length < 4) {
                    signalState = signalState.copy(sensor2 = signal.toInt())

                    if (validationState.contains(Field.Signal2))
                        _validationState.remove(Field.Signal2)
                }
            }

            Field.Signal3 -> {
                if (signal.isEmpty()) {
                    signalState = signalState.copy(sensor3 = Int.MIN_VALUE)
                }
                else if (signal.all { it.isDigit() } && signal.length < 4) {
                    signalState = signalState.copy(sensor3 = signal.toInt())

                    if (validationState.contains(Field.Signal3))
                        _validationState.remove(Field.Signal3)
                }
            }
        }
    }

    fun updateSignal() {
        var isValid: Boolean = true

        if (signalState.sensor1 == Int.MIN_VALUE) {
            _validationState.add(Field.Signal1)
            isValid = false
        }

        if (signalState.sensor2 == Int.MIN_VALUE) {
            _validationState.add(Field.Signal2)
            isValid = false
        }

        if (signalState.sensor3 == Int.MIN_VALUE) {
            _validationState.add(Field.Signal3)
            isValid = false
        }

        if (isValid) {
            viewModelScope.launch { signalRepository.updateSignal(signalState) }
        }
    }





}