package com.example.project.data.repositories

import com.example.project.data.daos.StrengthDao
import com.example.project.data.entities.Strength
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//interface IStrengthRepository {
//    fun getAllStrengths(): Flow<List<Strength?>>
//    suspend fun insertAllStrengths(strengths: List<Strength>)
//}

class StrengthRepository @Inject constructor(private val strengthDao: StrengthDao) {
    fun getAllStrengths(): Flow<List<Strength?>> = strengthDao.getStrengths()
    suspend fun insertAllStrengths(strengths: List<Strength>) = strengthDao.insertAll(strengths)
}