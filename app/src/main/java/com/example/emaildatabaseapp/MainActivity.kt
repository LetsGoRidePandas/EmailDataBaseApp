package com.example.emaildatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.emaildatabaseapp.db.Player
import com.example.emaildatabaseapp.db.PlayerDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button

    private lateinit var viewModel: PlayerViewModel
    private lateinit var playerRecyclerView: RecyclerView
    private lateinit var adapter: PlayerRecyclerViewAdapter
    private var isListItemClicked = false

    private lateinit var selectedPlayer: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        playerRecyclerView = findViewById(R.id.rvPlayer)

        val dao = PlayerDatabase.getInstance(application).playerDao
        val factory = PlayerViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(PlayerViewModel::class.java)

        saveButton.setOnClickListener {
            if (isListItemClicked) {
                updatePlayerData()
                clearInput()
            } else {
                savePlayerData()
                clearInput()
            }
        }

        clearButton.setOnClickListener {
            if (isListItemClicked) {
                deletePlayerData()
                clearInput()
            } else {
                clearInput()
            }
        }

        initRecyclerView()


    }

    private fun savePlayerData() {
        viewModel.insertPlayer(
            Player(
                0,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
    }

    private fun updatePlayerData() {
        viewModel.updatePlayer(
            Player(
                selectedPlayer.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun deletePlayerData() {
        viewModel.deletePlayer(
            Player(
                selectedPlayer.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun clearInput() {
        nameEditText.setText("")
        emailEditText.setText("")
    }

    private fun initRecyclerView() {

        playerRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlayerRecyclerViewAdapter { selectedItem: Player ->
            listItemClicked(selectedItem)
        }
        playerRecyclerView.adapter = adapter

        displayPlayersList()
    }

    private fun displayPlayersList() {
        viewModel.players.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(player: Player) {
//        Toast.makeText(
//            this,
//            "Player name is ${player.name}",
//            Toast.LENGTH_LONG
//        ).show()
        selectedPlayer = player
        saveButton.text = "Update"
        clearButton.text = "Delete"
        isListItemClicked = true
        nameEditText.setText(selectedPlayer.name)
        emailEditText.setText(selectedPlayer.email)

    }
}