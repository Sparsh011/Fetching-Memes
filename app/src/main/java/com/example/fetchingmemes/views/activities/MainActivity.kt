package com.example.fetchingmemes.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fetchingmemes.views.adapter.MemeAdapter
import com.example.fetchingmemes.R
import com.example.fetchingmemes.model.network.RetrofitInstance
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var rvMemes : RecyclerView
    private lateinit var memesAdapter: MemeAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
                progressBar.isVisible = false
            }
            else{
                Toast.makeText(this@MainActivity, "Unable to fetch memes", Toast.LENGTH_SHORT).show()
                return@launchWhenCreated
            }
        }
    }
}