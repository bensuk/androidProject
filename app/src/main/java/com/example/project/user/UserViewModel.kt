package com.example.project.user

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.AppDatabase
import com.example.project.data.entities.User
import com.example.project.data.repositories.UserRepository
import com.example.project.signal.Field
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    var userState by mutableStateOf(User(0, "XX:XX:XX:XX:XX:XX"))
        private set

    private var shouldUpdate: Boolean = false

    init {
        viewModelScope.launch {
            val user = userRepository.getUsers().map { it.firstOrNull() }.first()
            if (user != null) {
                userState = user
                shouldUpdate = true
            }
        }
    }

    fun updateMACAddressState(mac: String){
        print(mac.count { it != ':' })
        if (mac.count { it != ':' } <= 12) {
            userState = userState.copy(macAddress = mac)
        }
    }

    private fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        var count: Int = 0
        var formattedMac: String = ""

        for (char in user.macAddress.filter { it.isLetter() || it.isDigit() }) {
            if (count % 2 == 0 && count != 0) {
                formattedMac += ":$char"
            }
            else {
                formattedMac += char
            }

            count ++
        }

        if (shouldUpdate) userRepository.updateUser(user.copy(macAddress = formattedMac))
            else insertUser(user.copy(macAddress = formattedMac))
    }
}