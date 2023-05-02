package com.example.emaildatabaseapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.emaildatabaseapp.db.Player

class PlayerRecyclerViewAdapter(
    private val clickListener:(Player)->Unit
):RecyclerView.Adapter<PlayerViewHolder>()  {

    private val playerList = ArrayList<Player>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return PlayerViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(playerList[position],clickListener)
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    fun setList(players:List<Player>){
        playerList.clear()
        playerList.addAll(players)
    }

}


class PlayerViewHolder(private val view: View):RecyclerView.ViewHolder(view){
     fun bind(player: Player,clickListener:(Player)->Unit){
         val nameTextView = view.findViewById<TextView>(R.id.tvName)
         val emailTextView = view.findViewById<TextView>(R.id.tvEmail)
         nameTextView.text = player.name
         emailTextView.text = player.email
         view.setOnClickListener {
             clickListener(player)
         }
     }
}