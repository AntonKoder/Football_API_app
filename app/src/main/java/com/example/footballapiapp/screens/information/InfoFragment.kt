package com.example.footballapiapp.screens.information

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.footballapiapp.APP_ACTIVITY
import com.example.footballapiapp.R
import com.example.footballapiapp.TEAM
import com.example.footballapiapp.databinding.InfoFragmentBinding
import com.example.footballapiapp.models.ui.TeamUI

class InfoFragment : Fragment() {

    private lateinit var viewModel: InfoViewModel

    private var nullableBinding: InfoFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var observerOnTeam: Observer<TeamUI>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = InfoFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
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