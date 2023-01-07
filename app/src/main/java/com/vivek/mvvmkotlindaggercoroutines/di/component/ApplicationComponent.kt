package com.vivek.mvvmkotlindaggercoroutines.di.component

import android.content.Context
import com.vivek.mvvmkotlindaggercoroutines.App
import com.vivek.mvvmkotlindaggercoroutines.data.api.NetworkService
import com.vivek.mvvmkotlindaggercoroutines.data.repository.TopHeadlineRepository
import com.vivek.mvvmkotlindaggercoroutines.di.ApplicationContext
import com.vivek.mvvmkotlindaggercoroutines.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule :: class])
interface ApplicationComponent {

    fun inject(application: App)

    @ApplicationContext
    fun getContext():Context

    fun getNetworkService() : NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

}