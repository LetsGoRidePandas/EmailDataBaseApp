package com.example.emaildatabaseapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.emaildatabaseapp.db.PlayerDao
import java.lang.IllegalArgumentException

class PlayerViewModelFactory(
    private val dao: PlayerDao
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PlayerViewModel::class.java)){
            return PlayerViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}