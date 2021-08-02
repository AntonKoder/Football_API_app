package com.example.footballapiapp.screens.countries

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
import com.example.footballapiapp.databinding.CountriesFragmentBinding
import com.example.footballapiapp.di.components.DaggerNetworkComponent
import com.example.footballapiapp.di.components.NetworkComponent
import com.example.footballapiapp.models.ui.CountryUI
import com.example.footballapiapp.repository.network.NetworkRepository
import javax.inject.Inject


class CountriesFragment : Fragment() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    private lateinit var viewModel: CountriesViewModel

    private var nullableBinding: CountriesFragmentBinding? = null
    private val binding get() = nullableBinding!!

    private lateinit var adapter: CountriesAdapter
    private lateinit var observerOnCountriesList: Observer<List<CountryUI>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nullableBinding = CountriesFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkComponent = getNetworkComponent()
        networkComponent.injectInFragment(this)
    }

    override fun onStart() {
        super.onStart()
        val someViewModel: CountriesViewModel by viewModels {
            CountriesViewModelFactory(
                networkRepository
            )
        }
        viewModel = someViewModel

        adapter = CountriesAdapter()
        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.countriesRecyclerView.adapter = adapter
        observerOnCountriesList = Observer {
            val list = it
            adapter.setData(list)
        }

        viewModel.countriesList.observe(this, observerOnCountriesList)

        viewModel.getCountries()

        binding.countriesRecyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                requireActivity(),
                binding.countriesRecyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        val bundle = Bundle()
                        bundle.putString(
                            COUNTRY,
                            viewModel.countriesList.value?.get(position)?.name
                        )
                        APP_ACTIVITY.navController.navigate(
                            R.id.action_countriesFragment_to_teamsFragment,
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

const val COUNTRY = "country"