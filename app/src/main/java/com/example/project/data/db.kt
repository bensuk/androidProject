package com.example.project.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Singleton
@Database(
    entities = [User::class, Signal::class, Strengths::class, StrengthsGrid::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun signalDao(): SignalDao
    abstract fun strengthsDao(): StrengthsDao
    abstract fun strengthsGridDao(): StrengthsGridDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }

//    fun getInstance(): AppDatabase{
//        return INSTANCE!!
//    }
}
