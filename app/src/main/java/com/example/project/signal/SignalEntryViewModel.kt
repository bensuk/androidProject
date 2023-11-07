package com.example.project.signal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.UserLocation
import com.example.project.data.entities.Signal
import com.example.project.data.repositories.SignalRepository
import com.example.project.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignalEntryViewModel @Inject constructor(
    private val signalRepository: SignalRepository,
    private val userRepository: UserRepository,
    private val userLocation: UserLocation
    ): ViewModel() {

    var signalState by mutableStateOf(
        Signal(0, "", Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE))
        private set

    private val _validationState = mutableStateListOf<Field>()
    val validationState: List<Field> = _validationState


    //TODO pamąstyti dėl neužsetinto MAC adreso, ar jei dar nespėjo pafetchinti
    init {
        viewModelScope.launch {
            val user = userRepository.getUsers().map { it.firstOrNull() }.first()
            if (user != null) {
                signalState = signalState.copy(macAddress = user.macAddress)
            }
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

    suspend fun insertSignal() {
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
            val id = signalRepository.insertSignal(signalState)
            userLocation.addLocation(signalState.copy(id = id.toInt()))
        }
    }
}