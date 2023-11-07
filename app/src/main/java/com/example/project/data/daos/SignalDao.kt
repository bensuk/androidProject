package com.example.project.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.project.data.entities.Signal
import kotlinx.coroutines.flow.Flow

@Dao
interface SignalDao {
    @Query("SELECT * FROM Signal")
    fun getSignals(): Flow<List<Signal>>

    @Query("SELECT * FROM Signal WHERE id = :id")
    fun getSignal(id: Int): Flow<Signal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(signals: List<Signal>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOne(signal: Signal) : Long

    @Delete
    suspend fun deleteSignal(signal: Signal)

    @Update
    suspend fun updateSignal(signal: Signal)
}