package com.example.lastterm.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Activity.PlayMusicAcivity
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.squareup.picasso.Picasso
import java.util.ArrayList

class ListMusicAdapter(var context: Context, var mangbaihat: ArrayList<SongItem>) :
    RecyclerView.Adapter<ListMusicAdapter.ViewHolder>() {
    internal lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)

        view = inflater.inflate(R.layout.content_activity_playlist, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val baiHat = mangbaihat?.get(position)
        var STT:Int = 0
        holder.txttenbaihat.text = baiHat?.songname
        holder.txttencasi.text = baiHat?.singer
        Picasso.with(context).load(baiHat?.songImg).into(holder.hinhbaihat)

            holder.txtstt.text=(position+1).toString()
        view.setOnClickListener {
            val intent = Intent(context, PlayMusicAcivity::class.java)
            intent.putExtra("song",baiHat)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mangbaihat?.size!!
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttenbaihat: TextView
        var txttencasi: TextView
        var txtstt: TextView
        var hinhbaihat: ImageView
//        var tim: ImageView

        init {
            txttenbaihat = itemView.findViewById(R.id.textViewtenbaihat)
            txttencasi = itemView.findViewById(R.id.textViewtencasi)
            hinhbaihat = itemView.findViewById(R.id.imageViewhinhbaihat)
            txtstt=itemView.findViewById(R.id.stt)
//            tim = itemView.findViewById(R.id.imageViewtimdanhsachbaihat)
            itemView
        }

    }
}