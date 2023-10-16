package com.example.project.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {
    //TODO CHECK IF WORKS, WAS List<User>
    @Query("SELECT * FROM user")
    fun getUser(): List<User>

    //TODO MAYBE fun suspend
    @Insert
    suspend fun insertUser(vararg user: User)

    @Update
    suspend fun updateUser(vararg user: User)
}

@Dao
interface SignalDao {
    @Query("SELECT * FROM Signal")
    fun getSignals(): List<Signal>

    // TODO gal sito ir nereikes, nemanau kad reikes kitu vartotoju signalu
    @Insert
    fun insertAll(vararg signals: Signal)

    @Delete
    fun delete(vararg signal: Signal)

    @Update
    fun updateSignalr(vararg signal: Signal)
}

@Dao
interface StrengthsDao {
    @Query("SELECT * FROM Strengths")
    fun getStrengths(): List<Strengths>

    @Insert
    fun insertAll(vararg strengths: Strengths)
}

@Dao
interface StrengthsGridDao {
    @Query("SELECT * FROM StrengthsGrid")
    fun getStrengthsGrid(): List<StrengthsGrid>

    @Insert
    fun insertAll(vararg strengthsGrid: StrengthsGrid)
}