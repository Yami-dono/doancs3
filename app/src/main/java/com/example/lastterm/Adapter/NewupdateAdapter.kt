
package com.example.lastterm.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Activity.PlayMusicAcivity
import com.example.lastterm.Activity.Playlist
import com.example.lastterm.Model.SliderItem
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.example.lastterm.Server.DataService
import com.example.lastterm.Server.apiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class NewupdateAdapter(var context: Context, var mangthinhhanh: ArrayList<SongItem>) :
RecyclerView.Adapter<NewupdateAdapter.ViewHolder>() {

    internal lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = LayoutInflater.from(context).inflate(R.layout.content_newupdatesong, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newupdate= mangthinhhanh[position]

        Picasso.with(context).load(newupdate.songImg).into(holder.imgpnew)
        holder.txtnew.text= newupdate.songname
        holder.txtsinger.text=newupdate.singer
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(context, PlayMusicAcivity::class.java)
                intent.putExtra("song",newupdate)
                context.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {
        return  mangthinhhanh.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgpnew: ImageView
        var txtnew: TextView
        var txtsinger:TextView
        init {
            imgpnew = itemView.findViewById(R.id.imageviewimgnew)
            txtnew = itemView.findViewById(R.id.textviewsongname)
            txtsinger=itemView.findViewById(R.id.textviewsingernew)
        }
    }
}
