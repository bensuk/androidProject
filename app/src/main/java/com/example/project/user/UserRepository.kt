package com.example.project.user

import androidx.compose.ui.platform.LocalContext
import com.example.project.data.User
import com.example.project.data.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    // todo maybe with flow, but for now lets try that

    val readUser: Flow<List<User>> = userDao.getUser()

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }


}