package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName




data class Response (

	@SerializedName("league") val leagueInfo : LeagueInfo,
	@SerializedName("team") val team : Team,
	@SerializedName("form") val form : String,
	@SerializedName("fixtures") val fixtures : Fixtures,
	@SerializedName("goals") val goals : Goals,
	@SerializedName("biggest") val biggest : Biggest,
	@SerializedName("clean_sheet") val cleanSheet : CleanSheet,
	@SerializedName("failed_to_score") val failedToScore : FailedToScore,
	@SerializedName("penalty") val penalty : Penalty,
	@SerializedName("lineups") val lineups : List<Lineups>,
	@SerializedName("cards") val cards : Cards
)

data class CleanSheet (

	@SerializedName("home") val home : Int,
	@SerializedName("away") val away : Int,
	@SerializedName("total") val total : Int
)

data class FailedToScore (

	@SerializedName("home") val home : Int,
	@SerializedName("away") val away : Int,
	@SerializedName("total") val total : Int
)

data class Lineups (

	@SerializedName("formation") val formation : String,
	@SerializedName("played") val played : Int
)