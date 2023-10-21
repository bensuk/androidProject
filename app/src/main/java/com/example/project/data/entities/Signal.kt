package com.example.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Signal(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "mac_address") val macAddress: String,
    @ColumnInfo(name = "sensor1") val sensor1: String,
    @ColumnInfo(name = "sensor2") val sensor2: String,
    @ColumnInfo(name = "sensor3") val sensor3: String,
)