package com.example.footballapiapp.di.components

import com.example.footballapiapp.MainActivity
import com.example.footballapiapp.di.modules.NetworkModule
import com.example.footballapiapp.repository.network.APIService
import com.example.footballapiapp.repository.network.NetworkRepository
import com.example.footballapiapp.screens.countries.CountriesFragment
import com.example.footballapiapp.screens.information.InfoFragment
import com.example.footballapiapp.screens.statistics.StatisticsFragment
import com.example.footballapiapp.screens.teams.TeamsFragment
import com.google.gson.Gson
import dagger.Component
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface NetworkComponent {

    fun provideInterceptor(): HttpLoggingInterceptor

    fun provideOkHttpClient(): OkHttpClient

    fun provideGson(): Gson

    fun provideRetrofit(): Retrofit

    fun provideApiService(): APIService

    fun provideNetworkRepository(): NetworkRepository

    fun inject(main: MainActivity)
    fun injectInCountries(fr: CountriesFragment)
    fun injectInTeams(fr: TeamsFragment)
    fun injectInInfo(fr: InfoFragment)
    fun injectInStatistics(fr: StatisticsFragment)
}
