package com.gmail.lyohakasianik.leaguestatistics

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.gmail.lyohakasianik.leaguestatistics.entity.match.Match
import kotlinx.android.synthetic.main.layout_menu_start.*

class StartMenu : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_start)

        buttonShowListGames.setOnClickListener {
            val intent = Intent(this, ListGamesActivity::class.java)
            intent.putExtra("nameSummoner", editText.text.toString())
            startActivity(intent)
        }

        buttonShowFreeChampions.setOnClickListener {
            val intent = Intent(this, ListFreeChampionsActivity::class.java)
            startActivity(intent)
        }
    }

}
