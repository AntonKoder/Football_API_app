package com.example.footballapiapp.models.network.statistics

import com.google.gson.annotations.SerializedName



data class Penalty (

	@SerializedName("scored") val scored : Scored,
	@SerializedName("missed") val missed : Missed,
	@SerializedName("total") val total : Int
)


data class Scored (

	@SerializedName("total") val total : Int,
	@SerializedName("percentage") val percentage : String
)

data class Missed (

	@SerializedName("total") val total : Int,
	@SerializedName("percentage") val percentage : String
)