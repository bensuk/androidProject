package com.example.project.data.repositories

import com.example.project.data.daos.LocationDao
import com.example.project.data.entities.Location
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository @Inject constructor(private val locationDao: LocationDao) {
    fun getAllLocations(): Flow<List<Location>> = locationDao.getLocations()
    suspend fun insertLocation(location: Location) = locationDao.insertOne(location)
}