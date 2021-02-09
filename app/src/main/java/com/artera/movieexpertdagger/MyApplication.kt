package com.artera.movieexpertdagger

import android.app.Application
import com.artera.movieexpertdagger.core.di.CoreComponent
import com.artera.movieexpertdagger.core.di.DaggerCoreComponent
import com.artera.movieexpertdagger.core.utils.ReleaseTree
import com.artera.movieexpertdagger.di.AppComponent
import com.artera.movieexpertdagger.di.DaggerAppComponent
import timber.log.Timber

open class MyApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }else {
            Timber.plant(ReleaseTree())
        }
    }
}

