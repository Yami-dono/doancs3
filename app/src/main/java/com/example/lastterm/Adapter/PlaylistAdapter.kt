
package com.example.lastterm.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Activity.Playlist
import com.example.lastterm.Model.PlaylistItem
import com.example.lastterm.R
import com.squareup.picasso.Picasso

class PlaylistAdapter(var context: Context, var mangPlaylist: ArrayList<PlaylistItem>) :
    RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {
    internal lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = LayoutInflater.from(context).inflate(R.layout.content_playlist, parent, false)

        return ViewHolder(view)
    }



    override fun getItemCount(): Int {
        return  mangPlaylist.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgplaylist: ImageView
        var txtTitle: TextView

        init {
            imgplaylist = itemView.findViewById(R.id.imageview_playlist)
            txtTitle = itemView.findViewById(R.id.textview_playlist)

        }
    }

    override fun onBindViewHolder(holder: PlaylistAdapter.ViewHolder, position: Int) {
        val BXH = mangPlaylist[position]

        Picasso.with(context).load(BXH.playlistmg).into(holder.imgplaylist)
        holder.txtTitle.text= BXH.playlistname
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(context, Playlist::class.java)
                intent.putExtra("playlist_BXH", BXH)
                context.startActivity(intent)
            }

        })


    }
}
