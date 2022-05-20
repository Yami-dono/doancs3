package com.example.lastterm.Adapter

import android.content.Context

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater

import com.squareup.picasso.Picasso
import android.content.Intent
import android.view.View

import android.widget.TextView
import com.example.lastterm.Activity.Playlist
import com.example.lastterm.Model.AlbumItem
import com.example.lastterm.R
import de.hdodenhof.circleimageview.CircleImageView
import java.util.ArrayList

class SingerAdapter(var context: Context, var singer_arr: ArrayList<AlbumItem?>?) :
    RecyclerView.Adapter<SingerAdapter.ViewHolder>() {
    internal lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        view = inflater.inflate(R.layout.content_singer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val singer = singer_arr!![position]
        holder.txttennghesi.text = singer!!.singername
        Picasso.with(context).load(singer.albumimg).into(holder.imgnghesi)
        view!!.setOnClickListener {
            val intent = Intent(context, Playlist::class.java)
            intent.putExtra("playlist_singer", singer_arr!![position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return if (singer_arr != null) singer_arr!!.size else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgnghesi: CircleImageView
        var txttennghesi: TextView

        init {
            imgnghesi = itemView.findViewById(R.id.imageviewnghesi)
            txttennghesi = itemView.findViewById(R.id.textviewnghesi)
        }
    }
}