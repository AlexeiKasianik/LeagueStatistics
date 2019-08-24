package com.gmail.lyohakasianik.leaguestatistics.app

import android.app.Application
import androidx.room.Room
import com.gmail.lyohakasianik.leaguestatistics.database.AppDatabase

class App : Application() {
    private lateinit var database: AppDatabase

    companion object {

        @JvmStatic
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "name")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}