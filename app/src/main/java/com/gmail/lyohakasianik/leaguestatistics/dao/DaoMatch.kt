package com.gmail.lyohakasianik.leaguestatistics.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.entity.match.POI_TABLE_NAME


@Dao
interface DaoMatch {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(listMatch: MutableList<Match>)

    @Query("SELECT * FROM $POI_TABLE_NAME")
    fun get():MutableList<Match>
}