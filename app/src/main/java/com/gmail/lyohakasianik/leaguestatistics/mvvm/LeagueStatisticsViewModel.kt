package com.gmail.lyohakasianik.leaguestatistics.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.leaguestatistics.END_INDEX
import com.gmail.lyohakasianik.leaguestatistics.START_INDEX
import com.gmail.lyohakasianik.leaguestatistics.app.App
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.entity.match.ParticipantIdentitie
import com.gmail.lyohakasianik.leaguestatistics.repository.provideGamesIdRepository
import com.gmail.lyohakasianik.leaguestatistics.repository.provideMatchRepository
import com.gmail.lyohakasianik.leaguestatistics.repository.provideSummonerRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmResults

class LeagueStatisticsViewModel : ViewModel() {
    private var matchList: MutableList<Match> = mutableListOf()
    private var matchListDb: RealmResults<Match>? = null
    private var averagesList: MutableList<Long>? = null
    private var disposable: Disposable? = null
    private var realm: Realm = Realm.getInstance(App.instance.config)

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
            if (item.player?.summonerName == summonerName)
                positionSummoner = item.participantId
        }
        return positionSummoner
    }

    fun returnKDA(match: Match): String {
        var kda = 0.0
        for (item in match.participants) {
            if (item.participantId == match.idSummonerInGame)
                kda = (item.stats!!.kills + item.stats!!.assists) / item.stats!!.deaths.toDouble()
        }
        return kda.toString()
    }

    fun returnKDASlash(match: Match): String {
        var kda = ""
        for (item in match.participants) {
            if (item.participantId == match.idSummonerInGame)
                kda = "${item.stats!!.kills}/${item.stats!!.deaths}/${item.stats!!.deaths}"
        }
        return kda
    }

    fun getMinions(minions: Int, neutralMinions: Int) = minions + neutralMinions

    fun getWinAndLose(): String {
        var win = 0
        var lose = 0
        for (item in matchList) {
            for (itemTwo in item.participants)
                if (itemTwo.participantId == item.idSummonerInGame) {
                    if (itemTwo.stats!!.win) {
                        ++win
                    } else ++lose
                }
        }
        return "$win $lose"

    }

    fun getTime(long: Long): String {
        val min = long / 60
        val sec = long % 60
        return "$min.$sec"
    }

    fun addGameInDb() {
        realm.executeTransaction {
            realm.insert(matchList[0])
            val results: RealmResults<Match> = realm.where(Match::class.java).findAll()

            /*realm!!.deleteAll()*/
        }

    }

    fun getMatchesDb() {
        realm.executeTransaction {
            matchListDb = realm.where(Match::class.java).findAll()
            for (item in matchListDb!!) {
                matchList.add(item)
            }
        }
    }

    fun getAverages() {
        var gold = 0L
        var damage = 0L
        var lvl = 0L
        var visibility = 0L
        var minions = 0L
        var kills = 0L
        var death = 0L
        var assists = 0L
        getMatchesDb()
        for (item in matchList) {
            for (it in item.participants)
                if (it.participantId == matchList[0].idSummonerInGame) {
                    gold += it.stats!!.goldEarned.toLong()
                    damage += it.stats!!.totalDamageDealtToChampions
                    lvl += it.stats!!.champLevel.toLong()
                    visibility += it.stats!!.visionScore
                    minions += it.stats!!.totalMinionsKilled.toLong()
                    kills += it.stats!!.kills.toLong()
                    death += it.stats!!.deaths.toLong()
                    assists += it.stats!!.assists.toLong()
                }
        }

        val list: MutableList<Long> = mutableListOf(gold, damage, lvl, visibility, minions, kills, death, assists)

        averagesList =  returnListAverages(list)
        state.value = MVVMState.DataAverages(averagesList!!)
    }

    fun returnListAverages(list: MutableList<Long>): MutableList<Long> {
        val newList: MutableList<Long> = mutableListOf()

        for (item in list) {
            newList.add(item / matchListDb!!.size)
        }
        return newList
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}