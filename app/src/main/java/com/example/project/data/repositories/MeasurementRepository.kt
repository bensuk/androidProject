package com.example.project.data.repositories

import com.example.project.data.daos.MeasurementDao
import com.example.project.data.daos.UserDao
import com.example.project.data.entities.Measurement
import com.example.project.data.entities.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface IMeasurementRepository {
//    fun getAllMeasurements(): Flow<List<Measurement?>>
//    suspend fun insertAll(measurements: List<Measurement>)
//}
class MeasurementRepository @Inject constructor(private val measurementDao: MeasurementDao) {
    fun getAllMeasurements(): Flow<List<Measurement?>> = measurementDao.getStrengthsGrid()
    suspend fun insertAll(measurements: List<Measurement>) = measurementDao.insertAll(measurements)
}