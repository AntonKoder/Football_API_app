package com.example.footballapiapp.screens.countries

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.network.toCountryUI
import com.example.footballapiapp.models.ui.CountryUI
import com.example.footballapiapp.repository.network.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesViewModel(private val networkRepository: NetworkRepository) : ViewModel() {

    var countriesList = MutableLiveData<List<CountryUI>>()

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            val countriesNM = networkRepository.getCountries()
            val countriesUI = mutableListOf<CountryUI>()
            for (item in countriesNM) {
                countriesUI.add(item.toCountryUI())
            }
            countriesUI.map { CountryUI(it.name, it.code, it.flag?.replace("\\", "")) }
            Log.d("TAG", countriesUI[0].flag.toString())
            countriesList.postValue(countriesUI)
        }
    }
}

class CountriesViewModelFactory(private val networkRepository: NetworkRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        CountriesViewModel(networkRepository) as T
}
