package com.example.project

import com.example.project.data.entities.Measurement
import com.example.project.data.entities.Strength
import com.example.project.data.repositories.MeasurementRepository
import com.example.project.data.repositories.StrengthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject

data class StrengthResponse(
    val id: Int,
    val measurement: Int,
    val sensor: String,
    val strength: Int
)

data class MeasurementResponse(
    val measurement: Int,
    val x: Int,
    val y: Int,
    val distance: Float
)

interface ApiService  {
    @GET("strengths/")
    suspend fun getAllStrengths(): Response<List<StrengthResponse>>

    @GET("measurements/")
    suspend fun getAllMeasurements(): Response<List<MeasurementResponse>>
}

class ApiServiceClass @Inject constructor(private val measurementRepository: MeasurementRepository,
                                     private val strengthRepository: StrengthRepository) {
    suspend fun fetchData() {

        val API_URL = "http://10.0.2.2:5273/api/seklys/"

        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create<ApiService>(ApiService::class.java)

            withContext(Dispatchers.IO) {

                async {
                    val strengths: List<StrengthResponse>? = service.getAllStrengths().body()

                    var count = 1
                    val temp: MutableList<Int> = ArrayList<Int>()
                    val list: MutableList<Strength> = ArrayList<Strength>()

                    if (strengths != null) {
                        for (strength in strengths) {
                            temp.add(strength.strength)
                            if (count % 3 == 0) {
                                list.add(Strength(
                                    strength.id/3, strength.measurement, temp[0], temp[1], temp[2]))
                                temp.clear()
                            }
                            count++
                        }
                        strengthRepository.insertAllStrengths(list.toList())
                    }
                    else {
                        throw Exception("couldn't get strengths")
                    }
                }

                async {
                    val measurements: List<MeasurementResponse>? = service.getAllMeasurements().body()

                    val list: MutableList<Measurement> = ArrayList<Measurement>()

                    if (measurements != null) {
                        for (measurement in measurements) {

                            list.add(Measurement
                                (measurement.measurement, measurement.x, measurement.y))
                        }
                        measurementRepository.insertAll(list.toList())
                    }
                    else {
                        throw Exception("couldn't get measurements")
                    }
                }
            }
        }
        catch (e: Exception) {
            throw Exception(e)
        }
    }
}






