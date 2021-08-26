package com.example.footballapiapp.screens.preloader

import androidx.lifecycle.*
import com.example.footballapiapp.GLOBAL_LINK
import com.example.footballapiapp.MyApplication
import com.example.footballapiapp.repository.local.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreloaderViewModel(private val localRepository: LocalRepository, private val app: MyApplication) :
    AndroidViewModel(app) {

    var urlLiveData = MutableLiveData<String>()

    fun getCasinoRootUrl() {
        viewModelScope.launch(Dispatchers.IO) {

            urlLiveData.postValue(app.preferences.getString(GLOBAL_LINK, "http://46.161.53.135/rcwsmW"))
//            link
//            urlLiveData.postValue("http://46.161.53.135/rcwsmW")
            //error
//            urlLiveData.postValue("http://46.161.53.135/QMddJ7")
        }
    }
}

class PreloaderViewModelFactory(private val localRepository: LocalRepository, private val app: MyApplication) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PreloaderViewModel(localRepository, app) as T
}
