package com.my_project.thecatapiproject

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.my_project.thecatapiproject.cat_api_sample.AppComponent
import com.my_project.thecatapiproject.cat_api_sample.DaggerAppComponent
import com.my_project.thecatapiproject.cat_api_sample.NetworkModule

import timber.log.Timber

/**
 * Created Максим on 13.09.2019.
 * Copyright © Max
 */
class App:Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule(this))
            .build()

        initFresco()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }


    private fun initFresco() = Fresco.initialize(this)

}