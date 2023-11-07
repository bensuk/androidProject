package com.example.project.data.repositories

import com.example.project.data.daos.SignalDao
import com.example.project.data.entities.Signal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignalRepository @Inject constructor(private val signalDao: SignalDao) {
    fun getAllSignals(): Flow<List<Signal>> = signalDao.getSignals()
    fun getSignal(id: Int): Flow<Signal> = signalDao.getSignal(id)
    suspend fun insertAllSignals(signals: List<Signal>) = signalDao.insertAll(signals)
    suspend fun insertSignal(signal: Signal): Long = signalDao.insertOne(signal)
    suspend fun deleteSignal(signal: Signal) = signalDao.deleteSignal(signal)
    suspend fun updateSignal(signal: Signal) = signalDao.updateSignal(signal)
}