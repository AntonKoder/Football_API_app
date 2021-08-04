package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName

data class LeagueInfo (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("country") val country : String,
	@SerializedName("logo") val logo : String,
	@SerializedName("flag") val flag : String,
	@SerializedName("season") val season : Int
)