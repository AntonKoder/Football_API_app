package com.example.footballapiapp.screens.information

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.footballapiapp.R
import com.example.footballapiapp.TEAM
import com.example.footballapiapp.databinding.InfoFragmentBinding
import com.example.footballapiapp.databinding.TeamsFragmentBinding
import com.example.footballapiapp.models.ui.CountryUI
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.screens.teams.TeamsViewModel

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
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }

}