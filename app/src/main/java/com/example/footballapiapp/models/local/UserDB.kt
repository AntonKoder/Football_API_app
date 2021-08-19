package com.example.footballapiapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDB(
    @PrimaryKey
    @ColumnInfo(name = "valid")
    val valid: Boolean
)
