package com.gmail.lyohakasianik.leaguestatistics.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.lyohakasianik.leaguestatistics.MATCH_TABLE_NAME
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match

@Dao
interface MatchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(match: Match)

    @Query("SELECT * FROM $MATCH_TABLE_NAME")
    fun get():MutableList<Match>
}