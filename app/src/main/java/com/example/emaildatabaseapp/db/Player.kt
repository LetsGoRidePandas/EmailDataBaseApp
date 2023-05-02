package com.example.emaildatabaseapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_data_table")
data class Player(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "player_id")
    var id: Int,

    @ColumnInfo(name = "player_name")
    var name: String,

    @ColumnInfo(name = "player_email")
    var email: String

)