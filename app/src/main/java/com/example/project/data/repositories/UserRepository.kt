package com.example.project.data.repositories

import com.example.project.data.daos.UserDao
import com.example.project.data.entities.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {
    fun getUsers(): Flow<List<User>> = userDao.getUser()
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
}