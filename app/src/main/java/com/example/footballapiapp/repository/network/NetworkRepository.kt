package com.example.footballapiapp.repository.network

import com.example.footballapiapp.models.network.TeamNM
import com.example.footballapiapp.models.network.CountryNM
import com.example.footballapiapp.models.network.SeasonNM
import com.example.footballapiapp.models.network.league.LeagueNM
import com.example.footballapiapp.models.network.statistics.StatisticsNM


interface NetworkRepository {

    suspend fun getCountries(): List<CountryNM>
    suspend fun getTeams(countryName: String): List<TeamNM>
    suspend fun getSeasons(): SeasonNM
    suspend fun getLeague(season: String, countryName: String, teamId: String): LeagueNM
    suspend fun getTeamStatistics(teamId: String, season: String, leagueId: String): StatisticsNM

}