package com.suonk.drwhite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView


class StartPlayingActivity : AppCompatActivity() {

    //region ========================================== Val or Var ==========================================

    var startPlayingActivityPlayersCivilsNumbs: TextView? = null
    var startPlayingActivityPlayersUndercoverNumbs: TextView? = null
    var startPlayingActivityPlayersDrWhiteNumbs: TextView? = null
    var startPlayingActivityTextNumbers: TextView? = null

    var startPlayingActivitySeekBar: SeekBar? = null

    var startPlayingActivityPlayersUndercoverPlus: AppCompatImageView? = null
    var startPlayingActivityPlayersUndercoverMinus: AppCompatImageView? = null
    var startPlayingActivityPlayersMrWhiteMinus: AppCompatImageView? = null
    var startPlayingActivityPlayersMrWhitePlus: AppCompatImageView? = null

    var startPlayingActivityStartButton: AppCompatButton? = null

    var civilsNumbers = 2
    var undercoverNumbers = 0
    var drWhiteNumbers = 1
    var playersNumbers = 3

    var minCivilsNumbs = civilsNumbers

//    start_playing_players_mr_white_plus

    //endregion

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_playing)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //region ======================================= FindViewById =======================================

        startPlayingActivitySeekBar = findViewById(R.id.start_playing_players_seekbar)
        startPlayingActivityTextNumbers = findViewById(R.id.start_playing_players_text_numbers)

        startPlayingActivityPlayersCivilsNumbs = findViewById(R.id.start_playing_players_civils_text)
        startPlayingActivityPlayersUndercoverNumbs = findViewById(R.id.start_playing_players_undercover_text)
        startPlayingActivityPlayersDrWhiteNumbs = findViewById(R.id.start_playing_players_dr_white_text)

        startPlayingActivityPlayersUndercoverPlus = findViewById(R.id.start_playing_players_undercover_plus)
        startPlayingActivityPlayersUndercoverMinus = findViewById(R.id.start_playing_players_undercover_minus)
        startPlayingActivityPlayersMrWhiteMinus = findViewById(R.id.start_playing_players_mr_white_minus)
        startPlayingActivityPlayersMrWhitePlus = findViewById(R.id.start_playing_players_mr_white_plus)

        startPlayingActivityStartButton = findViewById(R.id.start_playing_start_button)

        //endregion

        startPlayingActivityPlayersCivilsNumbs!!.text = "$civilsNumbers Civils"
        startPlayingActivityPlayersUndercoverNumbs!!.text = "$undercoverNumbers Undercover"
        startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Dr. White"

        startPlayingActivitySeekBar!!.min = 15

        plusMinus()

        //region ==================================== SharedPreferences =====================================

        val nbCivilsSharedPreferences: SharedPreferences = getSharedPreferences("nbCivilsPreferences", Context.MODE_PRIVATE)
        val nbUndercoversSharedPreferences: SharedPreferences = getSharedPreferences("nbUndercoversPreferences", Context.MODE_PRIVATE)
        val nbDrWhitesSharedPreferences: SharedPreferences = getSharedPreferences("nbDrWhitesPreferences", Context.MODE_PRIVATE)

//        val importWhatsapp = importWhatsappSharedPreferences.getBoolean("importWhatsappPreferences", false)

        //endregion

        //region ======================================== Listeners =========================================

        startPlayingActivitySeekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // TODO Auto-generated method stub
            }

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // TODO Auto-generated method stub

                playersNumbers = seekBar.progress / 5
                startPlayingActivityTextNumbers!!.text = "$playersNumbers"

                // Players = 3, 2 civils 0 undercover 1 mr white
                // Players = 4, 3 civils 0 undercover 1 mr white
                // Players = 5, 3 civils 1 undercover 1 mr white
                // Players = 6, 4 civils 1 undercover 1 mr white
                // Players = 7, 4 civils 1 undercover 2 mr whites / playersNumbers - civilsNumbers = 3
                // Players = 8, 5 civils 1 undercover 2 mr whites
                // Players = 9, 5 civils 2 undercovers 2 mr whites
                // Players = 10, 6 civils 2 undercovers 2 mr whites
                // Players = 11, 7 civils 2 undercovers 2 mr whites
                // Players = 12, 7 civils 2 undercovers 3 mr whites

                civilsNumbers = (playersNumbers / 2) + 1
                minCivilsNumbs = civilsNumbers

                if (isEvenOrOdd(playersNumbers - civilsNumbers)) {
                    undercoverNumbers = (playersNumbers - civilsNumbers) / 2
                    drWhiteNumbers = (playersNumbers - civilsNumbers) / 2

                } else {
                    undercoverNumbers = (playersNumbers - civilsNumbers) / 2
                    drWhiteNumbers = ((playersNumbers - civilsNumbers) / 2) + 1
                }

                startPlayingActivityPlayersCivilsNumbs!!.text = "$civilsNumbers Civils"

                if (undercoverNumbers > 1) {
                    startPlayingActivityPlayersUndercoverNumbs!!.text =
                        "$undercoverNumbers Undercovers"
                } else {
                    startPlayingActivityPlayersUndercoverNumbs!!.text =
                        "$undercoverNumbers Undercover"
                }

                if (drWhiteNumbers > 1) {
                    startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Dr. Whites"
                } else {
                    startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Dr. White"
                }

                plusMinus()
            }
        })

        startPlayingActivityPlayersUndercoverPlus!!.setOnClickListener {
            undercoverNumbers += 1
            civilsNumbers -= 1

            if (undercoverNumbers > 1) {
                startPlayingActivityPlayersUndercoverNumbs!!.text =
                    "$undercoverNumbers Undercovers"
            } else {
                startPlayingActivityPlayersUndercoverNumbs!!.text =
                    "$undercoverNumbers Undercover"
            }
            startPlayingActivityPlayersCivilsNumbs!!.text = "$civilsNumbers Civils"
            plusMinus()
        }
        startPlayingActivityPlayersUndercoverMinus!!.setOnClickListener {
            undercoverNumbers -= 1

            if(undercoverNumbers == 0){
                drWhiteNumbers += 1
            }else{
                civilsNumbers += 1
            }

            if (undercoverNumbers > 1) {
                startPlayingActivityPlayersUndercoverNumbs!!.text =
                    "$undercoverNumbers Undercovers"
            } else {
                startPlayingActivityPlayersUndercoverNumbs!!.text =
                    "$undercoverNumbers Undercover"
            }

            if (drWhiteNumbers > 1) {
                startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Mr. Whites"
            } else {
                startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Mr. White"
            }

            startPlayingActivityPlayersCivilsNumbs!!.text = "$civilsNumbers Civils"
            plusMinus()
        }

        startPlayingActivityPlayersMrWhiteMinus!!.setOnClickListener {
            drWhiteNumbers -= 1

            if(drWhiteNumbers == 0){
                undercoverNumbers += 1
            }else{
                civilsNumbers += 1
            }

            if (drWhiteNumbers > 1) {
                startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Mr. Whites"
            } else {
                startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Mr. White"
            }

            if (undercoverNumbers > 1) {
                startPlayingActivityPlayersUndercoverNumbs!!.text =
                    "$undercoverNumbers Undercovers"
            } else {
                startPlayingActivityPlayersUndercoverNumbs!!.text =
                    "$undercoverNumbers Undercover"
            }

            startPlayingActivityPlayersCivilsNumbs!!.text = "$civilsNumbers Civils"
            plusMinus()
        }
        startPlayingActivityPlayersMrWhitePlus!!.setOnClickListener {
            drWhiteNumbers += 1
            civilsNumbers -= 1

            if (drWhiteNumbers > 1) {
                startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Mr. Whites"
            } else {
                startPlayingActivityPlayersDrWhiteNumbs!!.text = "$drWhiteNumbers Mr. White"
            }
            startPlayingActivityPlayersCivilsNumbs!!.text = "$civilsNumbers Civils"
            plusMinus()
        }

        startPlayingActivityStartButton!!.setOnClickListener {
            val editNbCivils: SharedPreferences.Editor = nbCivilsSharedPreferences.edit()
            editNbCivils.putInt("nbCivilsPreferences", civilsNumbers)
            editNbCivils.apply()

            val editNbUndercovers: SharedPreferences.Editor = nbUndercoversSharedPreferences.edit()
            editNbUndercovers.putInt("nbUndercoversPreferences", undercoverNumbers)
            editNbUndercovers.apply()

            val editNbDrWhites: SharedPreferences.Editor = nbDrWhitesSharedPreferences.edit()
            editNbDrWhites.putInt("nbDrWhitesPreferences", drWhiteNumbers)
            editNbDrWhites.apply()

            startActivity(Intent(this, GameActivity::class.java).putExtra("nbPlayers", playersNumbers))
        }

        //endregion
    }

    //region ========================================== Functions ===========================================

    fun isEvenOrOdd(n: Int): Boolean {
        return (n % 2) == 0
    }

    fun plusMinus() {
        if (undercoverNumbers == 0) {
            startPlayingActivityPlayersUndercoverMinus!!.visibility = View.GONE
        } else {
            startPlayingActivityPlayersUndercoverMinus!!.visibility = View.VISIBLE
        }

        if (drWhiteNumbers == 0) {
            startPlayingActivityPlayersMrWhiteMinus!!.visibility = View.GONE
        } else {
            startPlayingActivityPlayersMrWhiteMinus!!.visibility = View.VISIBLE
        }

        if (minCivilsNumbs == civilsNumbers) {
            startPlayingActivityPlayersMrWhitePlus!!.visibility = View.GONE
            startPlayingActivityPlayersUndercoverPlus!!.visibility = View.GONE
        } else {
            startPlayingActivityPlayersUndercoverPlus!!.visibility = View.VISIBLE
            startPlayingActivityPlayersMrWhitePlus!!.visibility = View.VISIBLE
        }
    }

    //endregion
}
