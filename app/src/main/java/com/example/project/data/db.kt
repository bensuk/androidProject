package com.example.project.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project.data.daos.LocationDao
import com.example.project.data.daos.MeasurementDao
import com.example.project.data.daos.SignalDao
import com.example.project.data.daos.StrengthDao
import com.example.project.data.daos.UserDao
import com.example.project.data.entities.Location
import com.example.project.data.entities.Measurement
import com.example.project.data.entities.Signal
import com.example.project.data.entities.Strength
import com.example.project.data.entities.User

@Database(
    entities = [User::class, Signal::class, Strength::class, Measurement::class, Location::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun signalDao(): SignalDao
    abstract fun strengthDao(): StrengthDao
    abstract fun measurementDao(): MeasurementDao
    abstract fun locationDao(): LocationDao

//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "app_database"
//                ).fallbackToDestructiveMigration().build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }
}
