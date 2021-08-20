package com.example.footballapiapp.screens.preloader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.models.local.UserDB
import com.example.footballapiapp.repository.local.LocalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreloaderViewModel(private val localRepository: LocalRepository) : ViewModel() {

    var urlLiveData = MutableLiveData<String>()
    var userLiveData = MutableLiveData<UserDB>()

    fun getCasinoRootUrl() {
        viewModelScope.launch(Dispatchers.IO) {
            urlLiveData.postValue("http://46.161.53.135/rcwsmW")
        }
    }

    fun saveUser(valid: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            // очищаем БД перед тем как сохранть валидность пользователя
            localRepository.delete()
            localRepository.saveUser(UserDB(valid))
        }
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userLiveData.postValue(localRepository.getUser())
        }
    }
}

class PreloaderViewModelFactory(private val localRepository: LocalRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PreloaderViewModel(localRepository) as T
}
