package com.example.project.map

import androidx.lifecycle.ViewModel
import com.example.project.data.repositories.LocationRepository
import com.example.project.data.repositories.MeasurementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(private val measurementRepository: MeasurementRepository,
                                private val locationRepository: LocationRepository): ViewModel() {

    val measurements = measurementRepository.getAllMeasurements()
    val locations = locationRepository.getAllLocations()
}