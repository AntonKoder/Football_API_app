package com.example.footballapiapp.di.components

import com.example.footballapiapp.di.modules.AppModule
import com.example.footballapiapp.screens.preloader.PreloaderFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface ApplicationComponent {


    fun inject(fr: PreloaderFragment)
}