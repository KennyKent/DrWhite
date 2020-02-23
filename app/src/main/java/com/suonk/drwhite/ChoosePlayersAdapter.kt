package com.suonk.drwhite

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.random.Random

class ChoosePlayersAdapter(
//    val nbOfPlayers : Int,
    private val listOfPlayers: ArrayList<String>, val context: Activity
) : RecyclerView.Adapter<ChoosePlayersAdapter.ViewHolder>() {

    var isClicked = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val viewItem = inflater.inflate(R.layout.item_choose_players, parent, false)

        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int {
        return listOfPlayers.size
//        return nbOfPlayers
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemChoosePlayersImage!!.setImageResource(randomDefaultImage())
        holder.itemChoosePlayersCardView!!.tag = position
        holder.itemChoosePlayersCardView!!.setOnClickListener {

            if (context is GameActivity) {
                context.recyclerViewItemClick()
            }
            holder.itemChoosePlayersLayout!!.visibility = View.GONE
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemChoosePlayersCardView: CardView? = null
        var itemChoosePlayersImage: AppCompatImageView? = null
        var itemChoosePlayersLayout: RelativeLayout? = null

        init {
            itemChoosePlayersCardView = itemView.findViewById(R.id.item_choose_players_cardview)
            itemChoosePlayersImage = itemView.findViewById(R.id.item_choose_players_image)
            itemChoosePlayersLayout = itemView.findViewById(R.id.item_choose_players_layout)
        }
    }

    private fun randomDefaultImage(): Int {

        return when (Random.nextInt(0, 9)) {
            0 -> R.drawable.ic_player_alien
            1 -> R.drawable.ic_player_batman
            2 -> R.drawable.ic_player_clown
            3 -> R.drawable.ic_player_deadpool
            4 -> R.drawable.ic_player_excited
            5 -> R.drawable.ic_player_potter
            6 -> R.drawable.ic_player_spiderman
            7 -> R.drawable.ic_player_superman
            8 -> R.drawable.ic_player_teddy
            9 -> R.drawable.ic_player_thor
            else -> R.drawable.ic_player_clown
        }
    }
}