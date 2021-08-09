package com.example.footballapiapp.screens.statistics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.network.toLeagueUI
import com.example.footballapiapp.models.network.toStatisticsUI
import com.example.footballapiapp.models.ui.LeagueUI
import com.example.footballapiapp.models.ui.StatisticsUI
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    var teamLiveData = MutableLiveData<TeamUI>()
    val onSuccessLiveData = MutableLiveData<Boolean>(false)
    val leagueLiveData = MutableLiveData<List<LeagueUI>>()
    var statisticsLiveData = MutableLiveData<StatisticsUI>()

    fun loadLeagues() {
        viewModelScope.launch(Dispatchers.IO) {
            val leaguesNM = networkRepository.getLeagues(
                teamLiveData.value?.country!!,
                teamLiveData.value?.id!!
            )
            leagueLiveData.postValue(leaguesNM.map { it.toLeagueUI() })
        }
    }

    fun loadStatistics(leagueId: String, season: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val statiticsNM =
                networkRepository.getTeamStatistics(teamLiveData.value?.id!!, season, leagueId)
            statisticsLiveData.postValue(statiticsNM.toStatisticsUI())
        }
    }

    fun success() {
        onSuccessLiveData.postValue(true)
    }

    fun setTeam(teamUI: TeamUI) {
        teamLiveData.value = teamUI
    }
}

class StatisticsViewModelFactory(private val networkRepository: NetworkRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        StatisticsViewModel(networkRepository) as T
}