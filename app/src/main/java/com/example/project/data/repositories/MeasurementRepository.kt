package com.example.project.data.repositories

import com.example.project.data.daos.MeasurementDao
import com.example.project.data.entities.Measurement
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MeasurementRepository @Inject constructor(private val measurementDao: MeasurementDao) {
    fun getAllMeasurements(): Flow<List<Measurement>> = measurementDao.getMeasurements()
    fun getMeasurement(measurement: Int): Flow<Measurement> =
        measurementDao.getMeasurement(measurement)

    suspend fun insertAll(measurements: List<Measurement>) = measurementDao.insertAll(measurements)
}