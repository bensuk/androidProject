package com.example.project.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Strength(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "measurement") val measurement: Int,
    @ColumnInfo(name = "strength1") val strength1: Int,
    @ColumnInfo(name = "strength2") val strength2: Int,
    @ColumnInfo(name = "strength3") val strength3: Int
)