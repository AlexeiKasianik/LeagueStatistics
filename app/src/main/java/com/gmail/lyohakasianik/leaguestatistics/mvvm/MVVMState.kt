package com.gmail.lyohakasianik.leaguestatistics.mvvm

import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match

sealed class MVVMState {
    class Data(val listMatch: MutableList<Match>) : MVVMState()
    class Error(val throwable: Throwable) : MVVMState()
}