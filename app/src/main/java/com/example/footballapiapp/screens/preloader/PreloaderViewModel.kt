package com.example.footballapiapp.screens.preloader

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.footballapiapp.GLOBAL_DEP
import com.example.footballapiapp.GLOBAL_LINK
import com.example.footballapiapp.GLOBAL_REG
import com.example.footballapiapp.GLOBAL_UID
import com.example.footballapiapp.MyApplication
import com.example.footballapiapp.repository.checkregdep.RegDepService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PreloaderViewModel(private val app: MyApplication) :
    AndroidViewModel(app) {

    var urlLiveData = MutableLiveData<String>()

    fun getCasinoRootUrl() {
        viewModelScope.launch(Dispatchers.IO) {
            urlLiveData.postValue(
                app.preferences.getString(
                    GLOBAL_LINK,
                    "http://46.161.53.135/rcwsmW"
                )
            )
        }
    }

    fun getRegistrationOrDepositData() {
        viewModelScope.launch(Dispatchers.IO) {

            if (!app.preferences.getBoolean(
                    GLOBAL_REG,
                    false
                ) ||
                !app.preferences.getBoolean(
                    GLOBAL_DEP,
                    false
                )
            ) {
                val uId = app.preferences.getString(GLOBAL_UID, "")
                val appId = app.applicationContext.packageName

                val service = RegDepService()
                val regDepDB = service.getRegistrationOrDepositData(uId!!, appId).execute().body()!!

                if (regDepDB.registration > 0) {
                    saveRegistration()
                }

                if (regDepDB.deposit > 0) {
                    saveDeposit()
                }
            }
        }
    }

    private fun saveRegistration() {
        val registration = true
        with(app.preferences.edit()) {
            putBoolean(
                GLOBAL_REG,
                registration
            )
            apply()
        }
    }

    private fun saveDeposit() {
        val deposit = true
        with(app.preferences.edit()) {
            putBoolean(
                GLOBAL_DEP,
                deposit
            )
            apply()
        }
    }
}

class PreloaderViewModelFactory(private val app: MyApplication) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        PreloaderViewModel(app) as T
}
