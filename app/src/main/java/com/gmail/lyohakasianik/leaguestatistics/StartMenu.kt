package com.gmail.lyohakasianik.leaguestatistics

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.layout_menu_start.*

class StartMenu : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_statistics_selected_games)

      /*  buttonShowFreeChampions.setOnClickListener {
            val intent = Intent(this, ListFreeChampionsActivity::class.java)
            startActivity(intent)
        }*/

    }
}