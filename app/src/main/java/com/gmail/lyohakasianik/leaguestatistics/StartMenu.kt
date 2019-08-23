package com.gmail.lyohakasianik.leaguestatistics

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.gmail.lyohakasianik.leaguestatistics.entity.gameId.GameId
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Participant
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Team
import com.gmail.lyohakasianik.leaguestatistics.repository.*
import kotlinx.android.synthetic.main.layout_menu_start.*

class StartMenu : Activity(), SummonerResult, GamesIdRepositoryResult, MatchInformResult {
    private var matchIdList: List<GameId>? = null
    private var participants: List<Participant>? = null

    override fun onDataReady(teams: List<Team>, participants: List<Participant>) {
        Log.e("QQQ", teams.toString())
        this.participants = participants
        Log.e("QQQ", this.participants.toString())
    }

    override fun onDataReady(matchIdList: List<GameId>) {
        Log.e("QQQ", matchIdList.toString())
        this.matchIdList = matchIdList
        Log.e("QQQ", this.matchIdList.toString())
         for (item in this.matchIdList!!){
             match.getMatchInform(item.gameId, API_KEY,this)
         }
    }


    override fun onDataReady(accountId: String, name: String, profileIconId: Int, summonerLevel: Int) {
        Log.e("QQQ", accountId)

        mathesId.getGamesId(accountId, 7, 0, API_KEY, this)

    }

    override fun onError(throwable: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private val summoner = provideSummonerRepository()
    private val mathesId = provideGamesIdRepository()
    private val match = provideMatchRepository()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_start)

        buttonShowListGames.setOnClickListener {
            summoner.getSummoner(editText.text.toString(), API_KEY, this)
        }

        /*  buttonShowFreeChampions.setOnClickListener {
              val intent = Intent(this, ListFreeChampionsActivity::class.java)
              startActivity(intent)
          }*/

    }
}