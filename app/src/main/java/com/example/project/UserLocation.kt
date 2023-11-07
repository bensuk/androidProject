package com.example.project

import com.example.project.data.entities.Location
import com.example.project.data.entities.Signal
import com.example.project.data.repositories.LocationRepository
import com.example.project.data.repositories.MeasurementRepository
import com.example.project.data.repositories.StrengthRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

data class EuclideanDistance(val distance: Float, val measurement: Int)

class UserLocation @Inject constructor(private val measurementRepository: MeasurementRepository,
                                       private val strengthRepository: StrengthRepository,
                                       private val locationRepository: LocationRepository)
{
    //todo updatelocation
    suspend fun addLocation(signal: Signal) {

        val strengths = strengthRepository.getAllStrengths()
            .filter { it.isNotEmpty() }.first()

        var euclidean = EuclideanDistance(Float.MAX_VALUE, 0)

        for (strength in strengths) {
            val tmpDistance = sqrt((signal.sensor1-strength.strength1).toFloat().pow(2)+
                    (signal.sensor2-strength.strength2).toFloat().pow(2)+
                    (signal.sensor3-strength.strength3).toFloat().pow(2))

            if (tmpDistance < euclidean.distance) {
                euclidean = euclidean.copy(
                    distance = tmpDistance,
                    measurement = strength.measurement
                )
            }
        }

        val measurement = measurementRepository.getMeasurement(euclidean.measurement)
            .filter { it != null }.first()

        val location = Location(0, signal.id, measurement.x, measurement.y, euclidean.distance)

        locationRepository.insertLocation(location)
    }
}