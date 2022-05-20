
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

class SliderAdapter(var context: Context, var mangthinhhanh: ArrayList<SongItem>) :
RecyclerView.Adapter<SliderAdapter.ViewHolder>() {

    internal lateinit var view: View

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        view = LayoutInflater.from(context).inflate(R.layout.content_slider, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val thinhHanh = mangthinhhanh[position]

        Picasso.with(context).load(thinhHanh.songImg).into(holder.imgpthinhhanh)
        holder.txttenthinhhanh.text= thinhHanh.songname
        holder.txtsinger.text=thinhHanh.singer
        view.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent(context, PlayMusicAcivity::class.java)
                intent.putExtra("song",thinhHanh)
                context.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {
        return  mangthinhhanh.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgpthinhhanh: ImageView
        var txttenthinhhanh: TextView
        var txtsinger:TextView
        init {
            imgpthinhhanh = itemView.findViewById(R.id.imageviewthinhhanh)
            txttenthinhhanh = itemView.findViewById(R.id.textviewthinhhanh)
            txtsinger=itemView.findViewById(R.id.textviewsinger)
        }
    }
}
