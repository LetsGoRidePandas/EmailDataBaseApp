package com.example.emaildatabaseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emaildatabaseapp.db.Player
import com.example.emaildatabaseapp.db.PlayerDao
import kotlinx.coroutines.launch

class PlayerViewModel(private val dao: PlayerDao): ViewModel() {

    val players = dao.getAllPlayers()

    fun insertPlayer(player: Player)=viewModelScope.launch {
        dao.insertPlayer(player)
    }

    fun updatePlayer(player: Player)=viewModelScope.launch {
        dao.updatePlayer(player)
    }

    fun deletePlayer(player: Player)=viewModelScope.launch {
        dao.deletePlayer(player)
    }


}