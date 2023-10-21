package com.example.project.signal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project.data.entities.Signal
import com.example.project.data.entities.User
import com.example.project.data.repositories.SignalRepository
import com.example.project.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignalEntryViewModel @Inject constructor(private val signalRepository: SignalRepository,
    private val userRepository: UserRepository): ViewModel() {

    var signalState by mutableStateOf(Signal(0, "", "", "", ""))
        private set

    init {
        viewModelScope.launch {
            val user = userRepository.getUsers()/*.map { it.firstOrNull() }*/.first()
            if (user != null) {
                signalState.copy(macAddress = "user.macAddress")
            }
        }
    }

    fun updateSignalState(signal: Signal, field: Field) {
        when (field) {
            Field.Signal1 -> if (signal.sensor1.all { it.isDigit()} && signal.sensor1.length < 4)
                signalState = signalState.copy(sensor1 = signal.sensor1)

            Field.Signal2 -> if (signal.sensor2.all { it.isDigit()} && signal.sensor2.length < 4)
                signalState = signalState.copy(sensor2 = signal.sensor2)

            Field.Signal3 -> if (signal.sensor3.all { it.isDigit()} && signal.sensor3.length < 4)
                signalState = signalState.copy(sensor3 = signal.sensor3)
        }
    }


/*



    fun updateMACAddressState(mac: String){
        userState = userState.copy(macAddress = mac)
    }

    private fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        if (shouldUpdate) userRepository.updateUser(user) else insertUser(user)
    }
}*/
}