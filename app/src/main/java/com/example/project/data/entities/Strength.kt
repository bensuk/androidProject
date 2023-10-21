package com.example.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Strength(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "measurement") val measurement: Int,
    @ColumnInfo(name = "sensor") val sensor: String,
    @ColumnInfo(name = "strength") val strength: Int
)