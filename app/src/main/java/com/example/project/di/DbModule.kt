package com.example.project.di

import android.content.Context
import androidx.room.Room
import com.example.project.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    //todo idk about allowMainThreadQueries
    fun provideDb(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, AppDatabase::class.java, "app_database"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    fun provideStrengthDao(database: AppDatabase) = database.strengthDao()

    @Provides
    fun provideSignalDao(database: AppDatabase) = database.signalDao()

    @Provides
    fun provideMeasurementDao(database: AppDatabase) = database.measurementDao()

    @Provides
    fun provideLocationDao(database: AppDatabase) = database.locationDao()




}