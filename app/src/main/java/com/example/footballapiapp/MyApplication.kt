package com.example.footballapiapp

import android.app.Application
import com.example.footballapiapp.di.components.ApplicationComponent
import com.example.footballapiapp.di.components.DaggerApplicationComponent
import com.example.footballapiapp.di.modules.AppModule
import javax.inject.Singleton

@Singleton
class MyApplication : Application() {

    val appComponent = DaggerApplicationComponent.builder()
        .appModule(AppModule(this))
        .build()

//    fun getAppComponent(): ApplicationComponent {
//        return appComponent
//    }

}
