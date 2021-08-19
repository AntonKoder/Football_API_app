package com.example.footballapiapp.di.dependencies

import com.example.footballapiapp.models.local.UserDB
import com.example.footballapiapp.repository.local.Dao
import com.example.footballapiapp.repository.local.LocalRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepoImpl @Inject constructor(private val dao: Dao) : LocalRepository {

    override suspend fun saveUser(user: UserDB) {
        dao.saveUser(user)
    }

    override suspend fun getUser(): UserDB {
        return dao.getUser()
    }

    override suspend fun delete() {
        dao.delete()
    }
}
