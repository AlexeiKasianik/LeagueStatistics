package com.gmail.lyohakasianik.leaguestatistics.app

import android.app.Application
import com.gmail.lyohakasianik.leaguestatistics.databaseIcon.MATCH_TABLE_NAME

import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    var config: RealmConfiguration? = null

    companion object {

        @JvmStatic
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        Realm.init(this)
        config = RealmConfiguration.Builder()
            .name(MATCH_TABLE_NAME)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}