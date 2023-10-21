package com.example.project.data.repositories

import com.example.project.data.daos.SignalDao
import com.example.project.data.entities.Signal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface ISignalRepository {
//    fun getAllSignals(): Flow<List<Signal?>>
//    suspend fun insertAllSignals(signals: List<Signal>)
//    suspend fun insertSignal(signal: Signal)
//    suspend fun deleteSignal(signal: Signal)
//    suspend fun updateSignal(signal: Signal)
//}

class SignalRepository @Inject constructor(private val signalDao: SignalDao) {
    fun getAllSignals(): Flow<List<Signal?>> = signalDao.getSignals()
    suspend fun insertAllSignals(signals: List<Signal>) = signalDao.insertAll(signals)
    suspend fun insertSignal(signal: Signal) = signalDao.insertOne(signal)
    suspend fun deleteSignal(signal: Signal) = signalDao.deleteSignal(signal)
    suspend fun updateSignal(signal: Signal) = signalDao.updateSignal(signal)
}