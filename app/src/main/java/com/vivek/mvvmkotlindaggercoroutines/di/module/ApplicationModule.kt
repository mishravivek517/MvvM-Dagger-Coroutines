package com.vivek.mvvmkotlindaggercoroutines.di.module

import android.app.Application
import android.content.Context
import com.vivek.mvvmkotlindaggercoroutines.data.api.NetworkService
import com.vivek.mvvmkotlindaggercoroutines.di.ApplicationContext
import com.vivek.mvvmkotlindaggercoroutines.di.BaseURL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: Application) {

    @ApplicationContext
    @Provides
    fun provideContext() : Context{
        return application
    }

    @BaseURL
    @Provides
    fun provideBaseUrl():String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(@BaseURL baseurl : String, gsonConverterFactory: GsonConverterFactory): NetworkService{

        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

}