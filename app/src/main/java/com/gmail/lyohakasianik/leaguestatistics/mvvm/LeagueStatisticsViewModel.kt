package com.gmail.lyohakasianik.leaguestatistics.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.leaguestatistics.END_INDEX
import com.gmail.lyohakasianik.leaguestatistics.START_INDEX
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.entity.match.ParticipantIdentitie
import com.gmail.lyohakasianik.leaguestatistics.repository.provideGamesIdRepository
import com.gmail.lyohakasianik.leaguestatistics.repository.provideMatchRepository
import com.gmail.lyohakasianik.leaguestatistics.repository.provideSummonerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LeagueStatisticsViewModel : ViewModel() {
    private var matchList: MutableList<Match> = mutableListOf()
    private var disposable: Disposable? = null

    private val summonerRepository = provideSummonerRepository()
    private val matchesIdRepository = provideGamesIdRepository()
    private val matchRepository = provideMatchRepository()

    val state: MutableLiveData<MVVMState> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<MVVMState>()
    }

    fun loadMatchList(summonerName: String, apiKey: String) {
        if (matchList.isEmpty()) {
            disposable = summonerRepository
                .getSummoner(summonerName, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data ->
                    loadListId(summonerName, data.accountId, END_INDEX, START_INDEX, apiKey)
                }
        }
    }

    fun loadListId(summonerName: String, accountId: String, endIndex: Int, startIndex: Int, apiKey: String) {
        disposable = matchesIdRepository
            .getGamesId(accountId, endIndex, startIndex, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                for (item in data.matchIdList) {
                    loadMatchInform(summonerName, item.gameId, apiKey)
                }
            }
    }

    fun loadMatchInform(summonerName: String, gameId: Long, apiKey: String) {
        disposable = matchRepository
            .getMatchInform(gameId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                matchList.add(
                    Match(
                        returnPositionSummoner(data.participantIdentities, summonerName),
                        summonerName,
                        gameId,
                        data.participantIdentities,
                        data.teams,
                        data.participants,
                        data.gameDuration,
                        data.gameCreation
                    )
                )

                matchList.sortBy { it.gameDuration }
                state.value = MVVMState.Data(matchList)
            }
    }


    fun returnPositionSummoner(participantIdentities: List<ParticipantIdentitie>, summonerName: String): Int {
        var positionSummoner = 0
        for (item in participantIdentities) {
            if (item.player.summonerName == summonerName)
                positionSummoner = item.participantId
        }
        return positionSummoner
    }

    fun returnKDA(match: Match): String {
        var kda = 0.0
        for (item in match.participants!!) {
            if (item.participantId == match.idSummonerInGame)
                kda = (item.stats.kills + item.stats.assists) / item.stats.deaths.toDouble()
        }
        return kda.toString()
    }

    fun returnKDASlash(match: Match): String {
        var kda = ""
        for (item in match.participants!!) {
            if (item.participantId == match.idSummonerInGame)
                kda = "${item.stats.kills}/${item.stats.deaths}/${item.stats.deaths}"
        }
        return kda
    }

    fun getMinions(minions: Int, neutralMinions: Int) = minions + neutralMinions

    fun getWinAndLose():String {
        var win = 0
        var lose = 0
        for (item in matchList) {
            for (itemTwo in item.participants!!)
                if (itemTwo.participantId == item.idSummonerInGame){
                    if (itemTwo.stats.win){
                        ++win
                    }else ++lose
                }
        }
        return "$win $lose"

    }

    fun getTime(long: Long): String {
        val min = long / 60
        val sec = long % 60
        return "$min.$sec"
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}