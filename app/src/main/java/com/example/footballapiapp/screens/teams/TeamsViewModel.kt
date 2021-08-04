package com.example.footballapiapp.screens.teams

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.network.TeamNM
import com.example.footballapiapp.models.network.toTeamUI
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import com.example.footballapiapp.screens.countries.CountriesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamsViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    var teamsLiveData = MutableLiveData<List<TeamUI>>()

    fun getTeams(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val teamsNM: List<TeamNM> = networkRepository.getTeams("France")
            Log.d("TAG", teamsNM.toString())
            val teamsUI = mutableListOf<TeamUI>()
            for (item in teamsNM) {
                teamsUI.add(item.toTeamUI())
            }

            teamsUI.map {
                TeamUI(
                    it.id,
                    it.name,
                    it.country,
                    it.founded,
                    it.national,
                    it.logo?.replace("\\", ""),
                    it.venueName,
                    it.address,
                    it.city,
                    it.capacity,
                    it.surface,
                    it.image?.replace("\\", "")
                )
            }

            teamsLiveData.postValue(teamsUI)
        }
    }
}

class TeamsViewModelFactory(private val networkRepository: NetworkRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        TeamsViewModel(networkRepository) as T
}