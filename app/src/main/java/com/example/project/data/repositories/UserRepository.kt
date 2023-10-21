package com.example.project.data.repositories

import com.example.project.data.daos.UserDao
import com.example.project.data.entities.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface IUserRepository {
//    fun getUsers(): Flow<List<User?>>
//    suspend fun insertUser(user: User)
//    suspend fun updateUser(user: User)
//}

class UserRepository @Inject constructor(private val userDao: UserDao) {
    fun getUsers(): Flow<List<User?>> = userDao.getUser()
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
}