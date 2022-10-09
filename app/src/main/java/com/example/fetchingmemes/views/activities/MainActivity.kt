package com.example.fetchingmemes.views.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.fetchingmemes.R
import com.example.fetchingmemes.model.entities.Meme
import com.example.fetchingmemes.model.network.RetrofitInstance
import com.example.fetchingmemes.viewmodel.MemeViewModel
import java.io.IOException

class MainActivity : AppCompatActivity() {
    //    Creating our ViewModel object
    private lateinit var memeViewModel: MemeViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var btnShowSaved: Button
    private var imgURL: String? = null
    private lateinit var btnSaveMeme: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memeViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[MemeViewModel::class.java]

        btnShowSaved = findViewById(R.id.btn_show_saved_memes)
        btnShowSaved.setOnClickListener{
            startActivity(Intent(this, SavedMemes::class.java))
        }
        btnSaveMeme = findViewById(R.id.btn_save_meme)
        btnSaveMeme.setOnClickListener{
            imgURL?.let {
                memeViewModel.insert(Meme(it))
            }
        }
        progressBar = findViewById(R.id.progressBar)
        val img : ImageView = findViewById(R.id.img_loads_here)
        lifecycleScope.launchWhenCreated {
            progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getMemes()
            } catch (e : IOException){
                Log.e("MainActivity", "IOException, You might not have internet connection!")
                progressBar.isVisible = false
                return@launchWhenCreated
            }

            if (response.isSuccessful && response.body() != null){
                Glide.with(this@MainActivity)
                    .load(response.body()!!.url)
                    .into(img)
                imgURL = response.body()!!.url
                progressBar.isVisible = false
            }
            else{
                Toast.makeText(this@MainActivity, "Unable to fetch memes", Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
        }
    }
}