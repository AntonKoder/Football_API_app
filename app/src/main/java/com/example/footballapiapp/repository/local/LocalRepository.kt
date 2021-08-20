package com.example.footballapiapp.repository.local

import com.example.footballapiapp.models.local.UserDB

interface LocalRepository {
    suspend fun saveUser(user: UserDB)
    suspend fun getUser(): UserDB
    suspend fun delete()
}
