package com.example.footballapiapp.models.ui

data class StatisticsUI(
    val homeMatchesPlayed: String,
    val awayMatchesPlayed: String,
    val totalMatchesPlayed: String,

    val homeWins: String,
    val awayWins: String,
    val totalWins: String,

    val homeDraws: String,
    val awayDraws: String,
    val totalDraws: String,

    val homeLoses: String,
    val awayLoses: String,
    val totalLoses: String,

    val homeGoalsForTotal: String,
    val awayGoalsForTotal: String,
    val totalGoalsForTotal: String,

    val homeGoalsForAverage: String,
    val awayGoalsForAverage: String,
    val totalGoalsForAverage: String,

    val homeGoalsAgainstTotal: String,
    val awayGoalsAgainstTotal: String,
    val totalGoalsAgainstTotal: String,

    val homeGoalsAgainstAverage: String,
    val awayGoalsAgainstAverage: String,
    val totalGoalsAgainstAverage: String,

    val leagueName: String,
    val leagueImage: String,

    val form: String
)
