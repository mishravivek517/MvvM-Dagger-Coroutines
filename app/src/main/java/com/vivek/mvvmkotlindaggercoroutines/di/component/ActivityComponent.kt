package com.vivek.mvvmkotlindaggercoroutines.di.component

import com.vivek.mvvmkotlindaggercoroutines.MainActivity
import com.vivek.mvvmkotlindaggercoroutines.di.ActivityScope
import com.vivek.mvvmkotlindaggercoroutines.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: MainActivity)
}