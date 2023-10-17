package com.example.project.user

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.data.AppDatabase
import com.example.project.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//@Hilt
class UserViewModel(application: Application): AndroidViewModel(application) {

    private val userData: Flow<List<User>>
    private val repository: UserRepository

    val testas = getApplication<Application>()

    private val db = AppDatabase.getDatabase(application)

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        userData = repository.readUser
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

}