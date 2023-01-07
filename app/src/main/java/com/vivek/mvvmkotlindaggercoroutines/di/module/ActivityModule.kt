package com.vivek.mvvmkotlindaggercoroutines.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vivek.mvvmkotlindaggercoroutines.data.repository.TopHeadlineRepository
import com.vivek.mvvmkotlindaggercoroutines.di.ActivityContext
import com.vivek.mvvmkotlindaggercoroutines.ui.adapter.TopHeadlineAdapter
import com.vivek.mvvmkotlindaggercoroutines.ui.viewmodels.TopHeadlineViewModel
import com.vivek.mvvmkotlindaggercoroutines.ui.viewmodels.ViewModelProviderFactory
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext() : Context{
        return activity
    }


    @Provides
    fun provideTopHeadlineViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())


}