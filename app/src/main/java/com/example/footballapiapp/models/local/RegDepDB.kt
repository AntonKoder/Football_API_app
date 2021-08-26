package com.example.footballapiapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "registration_deposit_table)")
data class RegDepDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deposit") val deposit: Int,
    @ColumnInfo(name = "registration") val registration: Int
)
