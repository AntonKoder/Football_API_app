package com.example.footballapiapp.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.footballapiapp.models.local.UserDB

@Dao
interface Dao {
    @Query("SELECT * FROM user_table")
    fun getUser(): UserDB

    @Insert(onConflict = REPLACE)
    fun saveUser(user: UserDB)

    @Query("DELETE FROM user_table")
    fun delete()
}
