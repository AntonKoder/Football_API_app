package com.example.footballapiapp.repository.checkregdep

import com.example.footballapiapp.BuildConfig
import com.example.footballapiapp.models.network.RegDepNM
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RegDepService {
    @GET("apyag4/check.php")
    fun getRegistrationOrDepositData(@Query("uid") userId: String, @Query("bundle") appIdentifier: String): Call<RegDepNM>

    companion object {
        operator fun invoke(): RegDepService {
            val gson: Gson = GsonBuilder()
                .setLenient()
                .create()

            val interceptor = HttpLoggingInterceptor().also {
                if (BuildConfig.DEBUG) {
                    it.level = HttpLoggingInterceptor.Level.BODY
                } else {
                    it.level = HttpLoggingInterceptor.Level.NONE
                }
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://sport-privacy.ru/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(RegDepService::class.java)

            return retrofit
        }
    }
}
