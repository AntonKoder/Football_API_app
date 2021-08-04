package com.example.footballapiapp.screens.statistics

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballapiapp.TEAM
import com.example.footballapiapp.databinding.StatisticsFragmentBinding
import com.example.footballapiapp.di.components.DaggerNetworkComponent
import com.example.footballapiapp.di.components.NetworkComponent
import com.example.footballapiapp.models.ui.StatisticsUI
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import javax.inject.Inject


class StatisticsFragment : Fragment() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    private lateinit var viewModel: StatisticsViewModel

    private var nullableBinding: StatisticsFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var observerOnStatistics: Observer<StatisticsUI>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val networkComponent = getNetworkComponent()
        networkComponent.injectInStatistics(this)
    }

    private fun getNetworkComponent(): NetworkComponent {
        return DaggerNetworkComponent.builder()
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = StatisticsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val vm: StatisticsViewModel by viewModels {
            StatisticsViewModelFactory(
                networkRepository
            )
        }
        viewModel = vm

        val bundle = this.arguments
        if (bundle != null) {
            viewModel.setTeam(bundle.getSerializable(TEAM) as TeamUI)
            Log.d("RAG", bundle.getSerializable(TEAM).toString())
        } else {
            Log.d("RAG", "WTF")
        }

        val observeOnTeam: Observer<TeamUI> = Observer {
            val team = it
            binding.teamName.text = it.name
            Glide.with(requireActivity()).load(team.logo)
                .into(binding.teamLogo)

        }

        viewModel.team.observe(this, observeOnTeam)

        observerOnStatistics = Observer {
            val stat = it
            binding.homeMatches.text = stat.homeMatchesPlayed
            binding.awayMatches.text = stat.awayMatchesPlayed
            binding.totalMatches.text = stat.totalMatchesPlayed

            binding.homeWins.text = stat.homeWins
            binding.awayWins.text = stat.awayWins
            binding.totalWins.text = stat.totalWins

            binding.homeDraws.text = stat.homeDraws
            binding.awayDraws.text = stat.awayDraws
            binding.totalDraws.text = stat.totalDraws

            binding.homeLoses.text = stat.homeLoses
            binding.awayLoses.text = stat.awayLoses
            binding.totalLoses.text = stat.totalLoses

            binding.homeGoalsTotal.text = stat.homeGoalsForTotal
            binding.awayGoalsTotal.text = stat.awayGoalsForTotal
            binding.totalGoalsTotal.text = stat.totalGoalsForTotal

            binding.homeGoalsAverage.text = stat.homeGoalsForAverage
            binding.awayGoalsAverage.text = stat.awayGoalsForAverage
            binding.totalGoalsAverage.text = stat.totalGoalsForAverage

            binding.homeAgainstTotal.text = stat.homeGoalsAgainstTotal
            binding.awayAgainstTotal.text = stat.awayGoalsAgainstTotal
            binding.totalAgainstTotal.text = stat.totalGoalsAgainstTotal

            binding.homeGoalsAgainstAverage.text = stat.homeGoalsAgainstAverage
            binding.awayGoalsAgainstAverage.text = stat.awayGoalsAgainstAverage
            binding.totalGoalsAgainstAverage.text = stat.totalGoalsAgainstAverage
        }

        viewModel.statisticsLiveData.observe(this, observerOnStatistics)

        viewModel.loadStatistics()
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }

}