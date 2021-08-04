import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2021 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Response (

	@SerializedName("league") val league : League,
	@SerializedName("country") val country : Country,
	@SerializedName("seasons") val seasons : List<Seasons>
)

data class League (

	@SerializedName("id") val id : Int,
	@SerializedName("name") val name : String,
	@SerializedName("type") val type : String,
	@SerializedName("logo") val logo : String
)


data class Country (

	@SerializedName("name") val name : String,
	@SerializedName("code") val code : String,
	@SerializedName("flag") val flag : String
)

data class Seasons (

	@SerializedName("year") val year : Int,
	@SerializedName("start") val start : String,
	@SerializedName("end") val end : String,
	@SerializedName("current") val current : Boolean,
	@SerializedName("coverage") val coverage : Coverage
)

data class Coverage (

	@SerializedName("fixtures") val fixtures : Fixtures,
	@SerializedName("standings") val standings : Boolean,
	@SerializedName("players") val players : Boolean,
	@SerializedName("top_scorers") val top_scorers : Boolean,
	@SerializedName("top_assists") val top_assists : Boolean,
	@SerializedName("top_cards") val top_cards : Boolean,
	@SerializedName("injuries") val injuries : Boolean,
	@SerializedName("predictions") val predictions : Boolean,
	@SerializedName("odds") val odds : Boolean
)

data class Fixtures (

	@SerializedName("events") val events : Boolean,
	@SerializedName("lineups") val lineups : Boolean,
	@SerializedName("statistics_fixtures") val statistics_fixtures : Boolean,
	@SerializedName("statistics_players") val statistics_players : Boolean
)