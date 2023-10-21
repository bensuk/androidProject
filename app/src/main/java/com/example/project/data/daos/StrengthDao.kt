package com.example.project.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project.data.entities.Strength
import kotlinx.coroutines.flow.Flow

@Dao
interface StrengthDao {
    @Query("SELECT * FROM Strength")
    fun getStrengths(): Flow<List<Strength>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(strengths: List<Strength>)
}