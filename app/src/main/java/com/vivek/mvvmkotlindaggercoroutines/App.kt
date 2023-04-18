package com.vivek.mvvmkotlindaggercoroutines

import android.app.Application
import com.vivek.mvvmkotlindaggercoroutines.di.component.ApplicationComponent
import com.vivek.mvvmkotlindaggercoroutines.di.component.DaggerApplicationComponent
import com.vivek.mvvmkotlindaggercoroutines.di.module.ApplicationModule

class App : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}