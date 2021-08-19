package com.example.footballapiapp.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.footballapiapp.models.local.UserDB

@Database(entities = [UserDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dao(): Dao


    companion object {
        private var dbInstanse: AppDatabase? = null

        fun getAppDatabaseInstance(context: Context): AppDatabase {
            if (dbInstanse == null){
                dbInstanse = Room.databaseBuilder<AppDatabase>(
                    context.applicationContext, AppDatabase::class.java,"db"
                ).build()
            }
            return dbInstanse!!
        }
    }

}