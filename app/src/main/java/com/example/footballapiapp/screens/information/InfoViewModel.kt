package com.example.footballapiapp.screens.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.footballapiapp.models.ui.CountryUI
import com.example.footballapiapp.models.ui.TeamUI

class InfoViewModel : ViewModel() {

    var teamLiveData = MutableLiveData<TeamUI>()

    fun setTeam(team: TeamUI) {
        teamLiveData.value = team
    }
}