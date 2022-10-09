package com.example.fetchingmemes.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fetchingmemes.R
import com.example.fetchingmemes.model.entities.Meme

class MemeAdapter(
    private val context: Context
) : RecyclerView.Adapter<MemeAdapter.MemesViewHolder>(){
    private val allMemesList = ArrayList<Meme>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.meme_design, parent, false)
        return MemesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemesViewHolder, position: Int) {
        Glide.with(context)
            .load(allMemesList[position].image)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return allMemesList.size
    }

    fun updateList(newNotesList : ArrayList<Meme>){
        allMemesList.clear()
        allMemesList.addAll(newNotesList)
        notifyDataSetChanged()
    }

    class MemesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.iv_meme)
    }
}