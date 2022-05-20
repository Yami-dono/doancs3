package com.example.lastterm.Adapter

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater

import com.squareup.picasso.Picasso
import android.widget.TextView
import android.content.Intent
import android.view.View
import android.widget.ImageView
import com.example.lastterm.Activity.PlayMusicAcivity
import com.example.lastterm.Model.SongItem

import com.example.lastterm.R
import java.util.ArrayList

class SearchAdapter(var context: Context, var mangbaihat: ArrayList<SongItem>) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.content_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baiHat = mangbaihat[position]
        holder.txttentimkiem.text = baiHat.songname
        holder.txtcasitimkiem.text = baiHat.singer
        Picasso.with(context).load(baiHat.songImg).into(holder.imganhtimkiem)
    }

    override fun getItemCount(): Int {
        return mangbaihat.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttentimkiem: TextView
        var txtcasitimkiem: TextView
        var imganhtimkiem: ImageView

        init {
            txttentimkiem = itemView.findViewById(R.id.txttennhac)
            txtcasitimkiem = itemView.findViewById(R.id.txtcasinhac)
            imganhtimkiem = itemView.findViewById(R.id.imgnhac)
            itemView.setOnClickListener {
                val intent = Intent(context, PlayMusicAcivity::class.java)
                intent.putExtra("song", mangbaihat[position])
                context.startActivity(intent)
            }
        }
    }
}