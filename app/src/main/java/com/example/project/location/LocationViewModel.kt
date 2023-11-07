package com.example.project.location

import androidx.lifecycle.ViewModel
import com.example.project.data.repositories.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationRepository: LocationRepository): ViewModel()
{
    val locations = locationRepository.getAllLocations()
}