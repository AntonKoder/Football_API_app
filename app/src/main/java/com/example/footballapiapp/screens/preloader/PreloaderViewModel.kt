package com.example.footballapiapp.screens.preloader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreloaderViewModel() : ViewModel() {

    var urlLiveData = MutableLiveData<String>()

    fun getCasino() {
        viewModelScope.launch(Dispatchers.IO) {
            urlLiveData.postValue("http://46.161.53.135/rcwsmW")
        }
    }

}

class PreloaderViewModelFactory() :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PreloaderViewModel() as T
}
