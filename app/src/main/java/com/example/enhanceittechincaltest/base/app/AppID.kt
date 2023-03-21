package com.example.enhanceittechincaltest.base.app

import android.app.Application

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.enhanceittechincaltest.BuildConfig
import com.example.enhanceittechincaltest.utils.core.SharedPrefHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AppID : Application() {
    companion object{
        lateinit var instance : AppID
    }

//    private lateinit var  mRoomDbAppDatabase: AppDatabase
//    fun getAppDB(): AppDatabase {
//        if(!::mRoomDbAppDatabase.isInitialized){
//            mRoomDbAppDatabase = AppDatabase.getInstance(instance)
//        }
//        return mRoomDbAppDatabase
//    }

    fun appConfig(): AppConfig {
        return AppConfig()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        instance = this
        init()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun init() {
//        this.mRoomDbAppDatabase =  AppDatabase.getInstance(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        SharedPrefHelper.init(this)
    }
}