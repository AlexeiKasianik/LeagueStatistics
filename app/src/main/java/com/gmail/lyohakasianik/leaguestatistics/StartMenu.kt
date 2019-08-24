package com.gmail.lyohakasianik.leaguestatistics

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import com.gmail.lyohakasianik.leaguestatistics.mvvm.LeagueStatisticsViewModel
import com.gmail.lyohakasianik.leaguestatistics.mvvm.MVVMState
import kotlinx.android.synthetic.main.layout_menu_start.*

class StartMenu : FragmentActivity() {

    private var viewModel: LeagueStatisticsViewModel? = null

    private val observable = Observer<MVVMState> { list ->
        when (list) {
            is MVVMState.Data -> {
                onDataReady(list.listMatch)
            }
            is MVVMState.Error -> {
                onError(list.throwable)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_start)


        viewModel = ViewModelProviders.of(this).get(LeagueStatisticsViewModel::class.java)

        buttonShowListGames.setOnClickListener {
            viewModel?.loadMatchList(editText.text.toString(), API_KEY)
            viewModel?.state?.observeForever(observable)
        }

        /*  buttonShowFreeChampions.setOnClickListener {
              val intent = Intent(this, ListFreeChampionsActivity::class.java)
              startActivity(intent)
          }*/
    }

    private fun onError(throwable: Throwable) {
        Toast.makeText(this, throwable.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun onDataReady(listMatch: MutableList<Match>){
        Log.e("QQQ",listMatch.toString())
        Log.e("QQQ",listMatch.size.toString())
    }
}
