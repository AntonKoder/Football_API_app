package com.example.footballapiapp.di.modules

import android.app.Application
import android.content.Context
import com.example.footballapiapp.di.dependencies.LocalRepoImpl
import com.example.footballapiapp.repository.local.AppDatabase
import com.example.footballapiapp.repository.local.Dao
import com.example.footballapiapp.repository.local.LocalRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideLocalRepository(dao: Dao): LocalRepository {
        return LocalRepoImpl(dao)
    }

    @Provides
    @Singleton
    fun provideDao(appDatabase: AppDatabase): Dao {
        return appDatabase.dao()
    }

    @Provides
    @Singleton
    fun getRoomDbInstance(): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(provideAppContext())
    }

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return application.applicationContext
    }
}