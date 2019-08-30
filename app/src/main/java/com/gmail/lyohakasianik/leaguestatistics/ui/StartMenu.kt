package com.gmail.lyohakasianik.leaguestatistics.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.gmail.lyohakasianik.leaguestatistics.DatabaseIdIcon
import com.gmail.lyohakasianik.leaguestatistics.R
import kotlinx.android.synthetic.main.layout_menu_start.*
import java.util.regex.Pattern

class StartMenu : FragmentActivity() {
    private lateinit var patternName: Pattern

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_menu_start)
        patternName = Pattern.compile("^[0-9A-Za-z_\\.]+\$")

        buttonShowListGames.setOnClickListener {
            if (!check(editText.text.toString())) {
                Toast.makeText(this, "Summoner name is incorrect", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Hello ${editText.text}", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListGamesActivity::class.java)
                intent.putExtra("nameSummoner", editText.text.toString())
                startActivity(intent)
            }
        }

        buttonShowStatistics.setOnClickListener {
            val intent = Intent(this, StatisticsGamesDb::class.java)
            startActivity(intent)
        }

    }

    fun check(name: String): Boolean {
        return patternName.matcher(name).matches()
    }
}
