package com.example.project.user

import androidx.compose.ui.platform.LocalContext
import com.example.project.data.User
import com.example.project.data.UserDao
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {
    // todo maybe with flow, but for now lets try that

    val user: User = userDao.getUser().first()

    suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }


}