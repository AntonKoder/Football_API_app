package com.example.footballapiapp.models.network

import com.example.footballapiapp.models.ui.CountryUI
import com.google.gson.annotations.SerializedName

data class CountryNM(
    @SerializedName("name")
    val name: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("flag")
    val flag: String?
)


fun CountryNM.toCountryUI(): CountryUI {
    return CountryUI(this.name, this.code, this.flag)
}