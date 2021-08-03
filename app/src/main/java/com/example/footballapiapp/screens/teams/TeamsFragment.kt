package com.example.footballapiapp.screens.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.footballapiapp.APP_ACTIVITY
import com.example.footballapiapp.R
import com.example.footballapiapp.TEAM
import com.example.footballapiapp.databinding.TeamsFragmentBinding
import com.example.footballapiapp.di.components.DaggerFragment
import com.example.footballapiapp.di.components.DaggerNetworkComponent
import com.example.footballapiapp.di.components.NetworkComponent
import com.example.footballapiapp.models.ui.TeamUI
import com.example.footballapiapp.repository.network.NetworkRepository
import com.example.footballapiapp.screens.countries.COUNTRY
import com.example.footballapiapp.screens.countries.CountriesViewModelFactory
import com.example.footballapiapp.screens.countries.RecyclerItemClickListener
import javax.inject.Inject

class TeamsFragment : Fragment(), DaggerFragment {

    private lateinit var viewModel: TeamsViewModel

    private var nullableBinding: TeamsFragmentBinding? = null
    private val binding get() = nullableBinding!!

    @Inject
    lateinit var networkRepository: NetworkRepository

    private lateinit var adapter: TeamsAdapter
    private lateinit var observerOnCountriesList: Observer<List<TeamUI>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = TeamsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkComponent = getNetworkComponent()
        networkComponent.injectInTeams(this)
    }

    override fun onStart() {
        super.onStart()
        val vm: TeamsViewModel by viewModels {
            TeamsViewModelFactory(
                networkRepository
            )
        }
        viewModel = vm

        adapter = TeamsAdapter()
        binding.teamsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.teamsRecyclerView.adapter = adapter
        observerOnCountriesList = Observer {
            val list = it
            adapter.setData(list)
        }

        viewModel.teamsLiveData.observe(this, observerOnCountriesList)

        viewModel.getTeams("france")

        binding.teamsRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireActivity(),
                binding.teamsRecyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        val bundle = Bundle()
                        bundle.putSerializable(
                            TEAM,
                            viewModel.teamsLiveData.value?.get(position)
                        )
                        APP_ACTIVITY.navController.navigate(
                            R.id.action_teamsFragment_to_infoFragment,
                            bundle
                        )
                    }
                })
        )

    }

    private fun getNetworkComponent(): NetworkComponent {
        return DaggerNetworkComponent.builder()
            .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        nullableBinding = null
    }
}