package com.example.footballapiapp.di.dependencies

import com.example.footballapiapp.models.network.CountryNM
import com.example.footballapiapp.repository.network.APIService
import com.example.footballapiapp.repository.network.NetworkRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepoImpl @Inject constructor(private val apiService: APIService) : NetworkRepository {

    @Throws(IOException::class, RuntimeException::class)
    override suspend fun getCountries(): List<CountryNM> {
        val call = apiService.getCountries()
        return call.execute().body()!!
    }
}