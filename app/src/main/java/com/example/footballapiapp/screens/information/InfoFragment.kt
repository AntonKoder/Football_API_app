package com.example.footballapiapp.screens.information

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballapiapp.APP_ACTIVITY
import com.example.footballapiapp.R
import com.example.footballapiapp.TEAM
import com.example.footballapiapp.databinding.InfoFragmentBinding
import com.example.footballapiapp.di.components.DaggerNetworkComponent
import com.example.footballapiapp.di.components.NetworkComponent
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import javax.inject.Inject

class InfoFragment : Fragment() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    private lateinit var viewModel: InfoViewModel

    private var nullableBinding: InfoFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var observerOnTeam: Observer<TeamUI>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = InfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val networkComponent = getNetworkComponent()
        networkComponent.injectInInfo(this)
    }

    private fun getNetworkComponent(): NetworkComponent {
        return DaggerNetworkComponent.builder()
            .build()
    }

    override fun onStart() {
        super.onStart()

        val vm: InfoViewModel by viewModels {
            InfoViewModelFactory(networkRepository)
        }
        viewModel = vm

        val bundle = this.arguments
        if (bundle != null) {
            viewModel.setTeam(bundle.getSerializable(TEAM) as TeamUI)
        }

        observerOnTeam = Observer {
            val team = it
            Glide.with(requireActivity()).load(team.logo)
                .into(binding.teamLogo)
            binding.teamName.text = team.name
            binding.founded.text = team.founded
            binding.venueName.text = team.venueName
            binding.venueCity.text = team.city
            binding.venueAddress.text = team.address
            Glide.with(requireActivity()).load(team.image)
                .into(binding.venueImage)
        }

        viewModel.teamLiveData.observe(this, observerOnTeam)

        binding.teamStatisticsButton.setOnClickListener {
            navigateToStatistics()
        }
    }

    private fun navigateToStatistics() {
        val bundle = Bundle()
        bundle.putSerializable(TEAM, viewModel.teamLiveData.value)
        APP_ACTIVITY.navController.navigate(R.id.action_infoFragment_to_statisticsFragment, bundle)
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }
}
