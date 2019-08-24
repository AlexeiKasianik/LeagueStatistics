package com.gmail.lyohakasianik.leaguestatistics.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gmail.lyohakasianik.leaguestatistics.dao.DaoMatch
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match

@Database(entities = [Match::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDaoMatch(): DaoMatch
}
