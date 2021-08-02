package com.example.footballapiapp.screens.teams

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.ui.TeamUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamsViewModel : ViewModel() {

    var teamsLiveData = MutableLiveData<List<TeamUI>>()

    fun getTeams(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            //
        }
    }

}