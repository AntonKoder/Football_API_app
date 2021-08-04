package com.example.footballapiapp.screens.statistics

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.network.statistics.toStatisticsUI
import com.example.footballapiapp.models.ui.StatisticsUI
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatisticsViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    var statisticsLiveData = MutableLiveData<StatisticsUI>()
    var team = MutableLiveData<TeamUI>()

    fun loadStatistics() {
        viewModelScope.launch(Dispatchers.IO) {
            val seasons = networkRepository.getSeasons()
            val league = networkRepository.getLeague(
                seasons.year2016.toString(), team.value?.country!!,
                team.value!!.getTeamId()
            )
            val stat = networkRepository.getTeamStatistics(
                team.value!!.getTeamId(),
                seasons.year2016.toString(),
                league.parameters.id.toString()
            )
            Log.d("stat_NM",stat.toString())
            Log.d("stat_UI",stat.toStatisticsUI().toString())
            statisticsLiveData.postValue(stat.toStatisticsUI())
        }

    }

    fun setTeam(teamUI: TeamUI) {
        team.value = teamUI
    }


}

class StatisticsViewModelFactory(private val networkRepository: NetworkRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        StatisticsViewModel(networkRepository) as T
}