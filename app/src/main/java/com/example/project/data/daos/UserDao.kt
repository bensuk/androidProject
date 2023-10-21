package com.example.project.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.project.data.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    // TODO CHECK IF WORKS, WAS List<User>
    // TODO MAYBE ADD NULLABLE

    //TODO so do i need List or should i use it like Flow<User>
    @Query("SELECT * FROM user")
    fun getUser(): Flow<List<User>>

    //TODO not sure if i should keep vararg
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}