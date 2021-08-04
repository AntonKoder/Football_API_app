package com.example.footballapiapp.di.dependencies

import com.example.footballapiapp.models.network.TeamNM
import com.example.footballapiapp.models.network.CountryNM
import com.example.footballapiapp.models.network.SeasonNM
import com.example.footballapiapp.models.network.league.LeagueNM
import com.example.footballapiapp.models.network.statistics.StatisticsNM
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

    @Throws(IOException::class, RuntimeException::class)
    override suspend fun getTeams(countryName: String): List<TeamNM> {
        val call = apiService.getTeams(countryName)
        return call.execute().body()!!
    }

    override suspend fun getSeasons(): SeasonNM {
        val call = apiService.getSeasons()
        return call.execute().body()!!
    }

    override suspend fun getLeague(season: String, countryName: String, teamId: String): LeagueNM {
        val call = apiService.getLeague(season, countryName, teamId)
        return call.execute().body()!!
    }

    override suspend fun getTeamStatistics(
        teamId: String,
        season: String,
        leagueId: String
    ): StatisticsNM {
        val call = apiService.getStatisticsByTeam(teamId,season,leagueId)
        return call.execute().body()!!
    }
}