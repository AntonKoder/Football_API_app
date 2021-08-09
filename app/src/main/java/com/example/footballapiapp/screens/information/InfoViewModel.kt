package com.example.footballapiapp.screens.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.network.toList
import com.example.footballapiapp.models.ui.CountryUI
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import com.example.footballapiapp.screens.countries.CountriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
