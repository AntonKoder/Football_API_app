package com.example.footballapiapp.screens.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballapiapp.R
import com.example.footballapiapp.TEAM
import com.example.footballapiapp.databinding.StatisticsFragmentBinding
import com.example.footballapiapp.di.components.DaggerNetworkComponent
import com.example.footballapiapp.di.components.NetworkComponent
import com.example.footballapiapp.models.ui.LeagueUI
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
    private lateinit var observerOnLeague: Observer<List<LeagueUI>>

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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = StatisticsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        initViewModel()

        val bundle = this.arguments
        if (bundle != null) {
            viewModel.setTeam(bundle.getSerializable(TEAM) as TeamUI)
        }

        initTeamObserver()
        initLeagueObserver()
        initStatisticsObserver()

        createSeasonSpinnerAdapter()

        initOnSuccessObserver()

        viewModel.loadLeagues()
    }

    private fun initOnSuccessObserver() {
        val observer = Observer<Boolean> { onSuccess ->
            if (onSuccess) {
                viewModel.leagueLiveData.value?.find { it.name == binding.leagueSpinner.selectedItem.toString() }
                    ?.let { league ->
                        viewModel.loadStatistics(
                            league.id,
                            binding.seasonSpinner.selectedItem.toString()
                        )
                    }
            }
        }
        viewModel.onSuccessLiveData.observe(this, observer)
    }

    private fun createSeasonSpinnerAdapter() {
        binding.leagueSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    val league = viewModel.leagueLiveData.value?.find {
                        it.name == parent.getItemAtPosition(
                            position
                        ).toString()
                    }
                    if (league != null) {
                        Glide.with(requireActivity()).load(league.logo).into(binding.leagueImage)
                    }

                    binding.seasonSpinner.adapter = viewModel.leagueLiveData.value?.find {
                        it.name == parent.getItemAtPosition(
                            position
                        ).toString()
                    }?.let {
                        ArrayAdapter<String>(
                            requireContext(),
                            R.layout.season_spinner_item,
                            it.seasons
                        )
                    }
                }

                viewModel.success()
            }
        }
    }

    private fun initViewModel() {
        val vm: StatisticsViewModel by viewModels {
            StatisticsViewModelFactory(
                networkRepository
            )
        }
        viewModel = vm
    }

    private fun initTeamObserver() {
        val observeOnTeam: Observer<TeamUI> = Observer {
            val team = it
            binding.teamName.text = it.name
            Glide.with(requireActivity()).load(team.logo)
                .into(binding.teamLogo)
        }
        viewModel.teamLiveData.observe(this, observeOnTeam)
    }

    private fun initLeagueObserver() {
        observerOnLeague = Observer { leaguesList ->
            val adapter = ArrayAdapter<String>(
                requireContext(),
                R.layout.league_spinner_item,
                leaguesList.map { it.name })
            binding.leagueSpinner.adapter = adapter
        }
        viewModel.leagueLiveData.observe(this, observerOnLeague)
    }

    private fun initStatisticsObserver() {
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

            binding.form.text = stat.form
        }

        viewModel.statisticsLiveData.observe(this, observerOnStatistics)
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }
}
