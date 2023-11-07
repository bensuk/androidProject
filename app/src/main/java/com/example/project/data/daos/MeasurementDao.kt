package com.example.project.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project.data.entities.Measurement
import kotlinx.coroutines.flow.Flow

@Dao
interface MeasurementDao {
    @Query("SELECT * FROM Measurement")
    fun getMeasurements(): Flow<List<Measurement>>

    @Query("SELECT * FROM Measurement WHERE measurement = :measurement")
    fun getMeasurement(measurement: Int): Flow<Measurement>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(strengthsGrid: List<Measurement>)
}