package com.suonk.drwhite

import android.content.Context
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.collections.ArrayList
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    //region ========================================== Val or Var ==========================================

    var gameActivityPlayersNumbers: TextView? = null

    var gameActivityRecyclerView: RecyclerView? = null
    var choosePlayersAdapter: ChoosePlayersAdapter? = null

    var playersNumbers = 0
    var playerNumber = 1
    var undercoverId = 0
    var undercoverCpt = 0
    var drWhiteId = 0
    var drWhiteCpt = 0

    var nbCivils = 3
    var nbUndercovers = 0
    var nbDrWhites = 1

    var secretWordKeyCivil = ""
    var secretWordKeyUndercover = ""
    var secretWordCivil = ""
    var secretWordUndercover = ""
    var secretWordDrWhite = ""

    var listOfPlayers: ArrayList<String> = ArrayList()
    //    var listOfKeys: ArrayList<String> = ArrayList()
    var listOfWords = mapOf(
        "fantasy" to "Seigneur des anneaux",
        "fantasy1" to "The Hobbit",
        "VOD" to "Netflix",
        "VOD1" to "Amazon Prime",
        "Musique streaming" to "Spotify",
        "Musique streaming" to "Deezer",
        "City" to "Boston",
        "City1" to "Los Angeles",
        "Sport US" to "Basket-ball",
        "Sport US1" to "Football US",
        "Sport" to "Football",
        "Sport1" to "Tennis",
        "Tennis GOAT" to "Roger Federer",
        "Tennis GOAT1" to "Rafael Nadal",
        "Basketball GOAT" to "Michael Jordan",
        "Basketball GOAT1" to "LeBron James",
        "Rappeur" to "Eminem",
        "Rappeur1" to "Kanye West",
        "Water" to "Volvic",
        "Water1" to "Evian",
        "Smartphone1" to "Android",
        "Smartphone" to "Apple",
        "Console1" to "PS4",
        "Console" to "Xbox",
        "Instrument jazz" to "Saxophone",
        "Instrument jazz1" to "Trompette"
    )

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //region ====================================== FindViewById =======================================

        gameActivityRecyclerView = findViewById(R.id.game_activity_players_recycler_view)
        gameActivityPlayersNumbers = findViewById(R.id.game_activity_players_numbers)

        //endregion

        //region ========================================= Intent ===========================================

        playersNumbers = intent.getIntExtra("nbPlayers", 3)

        //endregion

        //region ==================================== SharedPreferences =====================================

        val nbCivilsSharedPreferences: SharedPreferences =
            getSharedPreferences("nbCivilsPreferences", Context.MODE_PRIVATE)
        val nbUndercoversSharedPreferences: SharedPreferences =
            getSharedPreferences("nbUndercoversPreferences", Context.MODE_PRIVATE)
        val nbDrWhitesSharedPreferences: SharedPreferences =
            getSharedPreferences("nbDrWhitesPreferences", Context.MODE_PRIVATE)

        nbCivils = nbCivilsSharedPreferences.getInt("nbCivilsPreferences", 1)
        nbUndercovers = nbUndercoversSharedPreferences.getInt("nbUndercoversPreferences", 0)
        nbDrWhites = nbDrWhitesSharedPreferences.getInt("nbDrWhitesPreferences", 0)

        //endregion

        for (item in 1..playersNumbers) {
            listOfPlayers.add("")
        }

        val listOfKeys: ArrayList<String> = arrayListOf(
            "fantasy",
            "VOD",
            "Musique streaming",
            "City",
            "Sport US",
            "Sport",
            "Tennis GOAT",
            "Basketball GOAT",
            "Rappeur",
            "Water",
            "Smartphone",
            "Console",
            "Instrument jazz"
        )

        secretWordKeyCivil = listOfKeys[Random.nextInt(0, 11)]
        secretWordKeyUndercover = secretWordKeyCivil + "1"
        secretWordCivil = listOfWords[secretWordKeyCivil] ?: error("")

        if (nbUndercovers == 0 && nbDrWhites > 0) {
            secretWordDrWhite = "----------"
        } else if (nbUndercovers > 0 && nbDrWhites == 0) {
            secretWordUndercover = listOfWords[secretWordKeyUndercover] ?: error("")
        } else {
            secretWordDrWhite = "----------"
            secretWordUndercover = listOfWords[secretWordKeyUndercover] ?: error("")
        }

        gameActivityPlayersNumbers!!.text = "Joueur 1"

        choosePlayersAdapter = ChoosePlayersAdapter(listOfPlayers, this)
        gameActivityRecyclerView!!.layoutManager = GridLayoutManager(this, 3)
        gameActivityRecyclerView!!.adapter = choosePlayersAdapter
        gameActivityRecyclerView!!.recycledViewPool.setMaxRecycledViews(0, 0)

        alertDialogChooseACard()
    }

    fun recyclerViewItemClick() {
        alertDialogChooseAName()
        for (item in playerNumber..playersNumbers) {
            gameActivityPlayersNumbers!!.text = "Joueur $item"

            return
        }
    }

    private fun alertDialogChooseACard() {

        val inflater: LayoutInflater = layoutInflater
        val alertView: View = inflater.inflate(R.layout.alert_dialog_choose_a_card, null)
        val alertDialog = MaterialAlertDialogBuilder(this, R.style.AlertDialog)
            .setView(alertView)
            .show()

        alertDialog.setCanceledOnTouchOutside(false)

        val alertDialogChooseACardButton =
            alertView.findViewById<AppCompatButton>(R.id.alert_dialog_choose_a_card_button)
        alertDialogChooseACardButton.setOnClickListener {
            alertDialog.cancel()
        }

        val alertDialogChooseACardPlayer =
            alertView.findViewById<TextView>(R.id.alert_dialog_choose_a_card_player)


        for (item in playerNumber..playersNumbers) {
            alertDialogChooseACardPlayer!!.text = "Joueur $item"
            return
        }
    }

    private fun alertDialogChooseAName() {

        val inflater: LayoutInflater = layoutInflater
        val alertView: View = inflater.inflate(R.layout.alert_dialog_choose_a_name, null)
        val alertDialog = MaterialAlertDialogBuilder(this, R.style.AlertDialog)
            .setView(alertView)
            .show()

        alertDialog.setCanceledOnTouchOutside(false)

        val alertDialogChooseANameButtonImport =
            alertView.findViewById<AppCompatButton>(R.id.alert_dialog_choose_a_name_import)
        val alertDialogChooseANameButtonSecretWord =
            alertView.findViewById<AppCompatButton>(R.id.alert_dialog_choose_a_name_done)
        val alertDialogChooseANameEditText =
            alertView.findViewById<AppCompatEditText>(R.id.alert_dialog_choose_a_name_edit_text)
        val alertDialogChooseANameLimitChar =
            alertView.findViewById<TextView>(R.id.alert_dialog_choose_a_name_limit_char)

        alertDialogChooseANameButtonImport.setOnClickListener {
            //            alertDialog.cancel()
        }

        alertDialogChooseANameButtonSecretWord.setOnClickListener {
            alertDialog.cancel()
            alertDialogSecretWord()
        }

        alertDialogChooseANameEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                alertDialogChooseANameLimitChar.text =
                    "${alertDialogChooseANameEditText.text!!.length} / 20"

                if (alertDialogChooseANameEditText.text!!.isNotEmpty()) {
                    alertDialogChooseANameButtonImport.visibility = View.GONE
                    alertDialogChooseANameButtonSecretWord.visibility = View.VISIBLE
                } else {
                    alertDialogChooseANameButtonSecretWord.visibility = View.GONE
                    alertDialogChooseANameButtonImport.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun alertDialogSecretWord() {

        val inflater: LayoutInflater = layoutInflater
        val alertView: View = inflater.inflate(R.layout.alert_dialog_your_word, null)
        val alertDialog = MaterialAlertDialogBuilder(this, R.style.AlertDialog)
            .setView(alertView)
            .show()

        alertDialog.setCanceledOnTouchOutside(false)

        val alertDialogYourWordOKButton =
            alertView.findViewById<AppCompatButton>(R.id.alert_dialog_your_word_done)
        val alertDialogYourWordSecretWord =
            alertView.findViewById<TextView>(R.id.alert_dialog_your_word_secret_word)

        //pN = 1,  pBs = 5
        //pNs = 6,  nbCivils = 4, nbDrWhites = 2, nbUndercovers = 0
        //
        if (nbUndercovers == 0 && nbDrWhites > 0) {
            while (drWhiteCpt < nbDrWhites) {
                drWhiteId = Random.nextInt(playerNumber, playersNumbers - 1)

                break
            }
        } else if (nbUndercovers > 0 && nbDrWhites == 0) {
            while (undercoverCpt < nbUndercovers) {
                undercoverId = Random.nextInt(playerNumber, playersNumbers - 1)
                break
            }
        } else {
//            do {
//                while (drWhiteCpt < nbDrWhites) {
//                    drWhiteId = Random.nextInt(playerNumber, playersNumbers)
//
//                    break
//                }
//
//                while (undercoverCpt < nbUndercovers) {
//                    undercoverId = Random.nextInt(playerNumber, playersNumbers)
//
//                    break
//                }
//            } while (drWhiteId == undercoverId)
        }

        when (playerNumber) {
            undercoverId -> {
                alertDialogYourWordSecretWord.text = secretWordUndercover
                undercoverCpt++
            }
            drWhiteId -> {
                alertDialogYourWordSecretWord.text = secretWordDrWhite
                drWhiteCpt++
            }
            else -> alertDialogYourWordSecretWord.text = secretWordCivil
        }

        alertDialogYourWordOKButton.setOnClickListener {
            playerNumber++

            if (playerNumber > playersNumbers) {
            } else {
                alertDialogChooseACard()
            }

            alertDialog.cancel()
        }
    }
}