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

//    val userState: StateFlow<User?> = userRepository.getUsers()
//        .map { it.firstOrNull() }
//        .stateIn(
//            scope = viewModelScope,
//            started = SharingStarted.WhileSubscribed(5000),
//            initialValue = User(0, "XX:XX:XX:XX:XX:XX:XX")
//        )

    var userState by mutableStateOf(User(0, "XX:XX:XX:XX:XX:XX:XX"))
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
        userState = userState.copy(macAddress = mac)
    }

    private fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        if (shouldUpdate) userRepository.updateUser(user) else insertUser(user)
    }
}