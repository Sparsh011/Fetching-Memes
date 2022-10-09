package com.example.fetchingmemes.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchingmemes.R
import com.example.fetchingmemes.model.entities.Meme
import com.example.fetchingmemes.viewmodel.MemeViewModel
import com.example.fetchingmemes.views.adapter.MemeAdapter

class SavedMemes : AppCompatActivity() {
    private lateinit var memeViewModel: MemeViewModel
    private lateinit var rvMemes: RecyclerView
    private lateinit var memesAdapter: MemeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_memes)

        rvMemes = findViewById(R.id.rv_saved_memes)
        rvMemes.layoutManager = LinearLayoutManager(this)
        memesAdapter = MemeAdapter(this)
        rvMemes.adapter = memesAdapter

        memeViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MemeViewModel::class.java]

        memeViewModel.allMemes.observe(this) { list ->
            list?.let {
                memesAdapter.updateList(it as ArrayList<Meme>)
            }
        }
    }
}