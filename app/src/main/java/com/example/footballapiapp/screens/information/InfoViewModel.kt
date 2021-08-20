package com.example.footballapiapp.screens.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository

class InfoViewModel(private val networkRepository: NetworkRepository) : ViewModel() {
    var teamLiveData = MutableLiveData<TeamUI>()

    fun setTeam(team: TeamUI) {
        teamLiveData.value = team
    }
}

class InfoViewModelFactory(private val networkRepository: NetworkRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        InfoViewModel(networkRepository) as T
}
