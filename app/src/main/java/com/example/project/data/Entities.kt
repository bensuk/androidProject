package com.example.project.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "mac_address") val macAddress: String
)

@Entity
data class Signal(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "mac_address") val macAddress: String,
    @ColumnInfo(name = "sensor") val sensor: String,
    @ColumnInfo(name = "strength") val strength: Int
)

@Entity
data class Strengths(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "measurement") val measurement: Int,
    @ColumnInfo(name = "sensor") val sensor: String,
    @ColumnInfo(name = "strength") val strength: Int
)

@Entity
data class StrengthsGrid(
    @PrimaryKey(autoGenerate = true) val measurement: Int,
    @ColumnInfo(name = "x") val x: Int,
    @ColumnInfo(name = "y") val y: Int,
    @ColumnInfo(name = "distance") val distance: Float
)