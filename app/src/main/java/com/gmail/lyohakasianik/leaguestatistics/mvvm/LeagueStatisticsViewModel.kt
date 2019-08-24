package com.gmail.lyohakasianik.leaguestatistics.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
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
    val selectedItem: MutableLiveData<Match> by lazy(LazyThreadSafetyMode.NONE) {
        MutableLiveData<Match>()
    }


    fun loadMatchList(summonerName: String, apiKey: String) {
        if (matchList.isEmpty()) {
            disposable = summonerRepository
                .getSummoner(summonerName, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data ->
                    loadListId(data.accountId, 7, 0, apiKey)
                }
        }
    }

    fun loadListId(accountId: String, endIndex: Int, startIndex: Int, apiKey: String) {
        disposable = matchesIdRepository
            .getGamesId(accountId, endIndex, startIndex, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                for (item in data.matchIdList) {
                    loadMatchInform(item.gameId, apiKey)
                }
            }
    }

    fun loadMatchInform(gameId: Long, apiKey: String) {
        disposable = matchRepository
            .getMatchInform(gameId, apiKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { data ->
                matchList.add(Match(data.teams, data.participants))
                state.value = MVVMState.Data(matchList)
            }
    }

    fun clickItem(item: Match) {
        selectedItem.value = item
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}