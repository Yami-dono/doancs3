package com.example.lastterm.Activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Adapter.ListMusicAdapter
import com.example.lastterm.Model.AlbumItem
import com.example.lastterm.Model.PlaylistItem
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.example.lastterm.Server.apiService
import com.example.lastterm.databinding.ActivityPlaylistBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.ArrayList

class Playlist : AppCompatActivity() {
    var song_arr: ArrayList<SongItem>? = null
    var BXH: PlaylistItem? = null
    var Album: AlbumItem? = null
    var recyclerViewdanhsachbaihat: RecyclerView? = null
    var listmusicAdapter: ListMusicAdapter? = null
    private lateinit var binding: ActivityPlaylistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        binding = ActivityPlaylistBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        innit()
        binding.floatingactionbutton!!.isEnabled = false
        DataIntent()

        if (BXH != null ) {
            setValueInView(BXH!!.playlistmg!!, BXH!!.playlistname!!)
            GetDataPlaylist((BXH!!.playlistid).toString())
        }
        if (Album != null) {
            setValueInView(Album!!.albumimg!!, Album!!.albumname!!)
            GetDataPlaylist_Singer((Album!!.singername).toString())
        }
        floatActionButtonClick()
    }


    fun DataIntent() {
        val intent: Intent = getIntent()


        if (intent.hasExtra("playlist_BXH")) {
           BXH =
                intent.getSerializableExtra("playlist_BXH") as PlaylistItem?
            supportActionBar!!.setTitle(BXH!!.playlistname)

        }
        if (intent.hasExtra("playlist_singer")) {
            Album =
                intent.getSerializableExtra("playlist_singer") as AlbumItem?
            supportActionBar!!.setTitle(Album!!.albumname)

        }
    }


    fun setValueInView(hinh: String, ten: String) {
        binding.textViewcollapsing.text = ten
        Picasso.with(applicationContext).load(hinh).into(binding.imageviewdanhsachcakhuc)
    }

    private fun innit() {
        var toolbar = binding.toolbardanhsachbaihat
        setSupportActionBar(toolbar)
        supportActionBar!!.setTitle("")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.setNavigationOnClickListener(View.OnClickListener { finish() })
        recyclerViewdanhsachbaihat = binding.recyclerviewdanhsachbaihat

    }

    private fun GetDataPlaylist(playlistId: String) {
        val dataservice = apiService.getService()

        val callback = dataservice.getlistBXH(playlistId)
        callback?.enqueue(object : Callback<List<SongItem>> {


            override fun onResponse(
                call: Call<List<SongItem>>,
                response: Response<List<SongItem>>,
            ) {
                song_arr = response.body() as ArrayList<SongItem>

                listmusicAdapter =
                    ListMusicAdapter(this@Playlist, song_arr!!)
                recyclerViewdanhsachbaihat!!.layoutManager =
                    LinearLayoutManager(this@Playlist)
                recyclerViewdanhsachbaihat!!.adapter = listmusicAdapter
            }

            override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {

            }
        })

    }
    private fun GetDataPlaylist_Singer(singerName: String) {
        val dataservice = apiService.getService()

        val callback = dataservice.getlistAlbum(singerName)
        callback?.enqueue(object : Callback<List<SongItem>> {


            override fun onResponse(
                call: Call<List<SongItem>>,
                response: Response<List<SongItem>>,
            ) {
                song_arr = response.body() as ArrayList<SongItem>

                listmusicAdapter =
                    ListMusicAdapter(this@Playlist, song_arr!!)
                recyclerViewdanhsachbaihat!!.layoutManager =
                    LinearLayoutManager(this@Playlist)
                recyclerViewdanhsachbaihat!!.adapter = listmusicAdapter
            }

            override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {

            }
        })

    }

    private fun floatActionButtonClick() {
        binding.floatingactionbutton!!.isEnabled = true
        binding.floatingactionbutton!!.setOnClickListener {
            val intent = Intent(this@Playlist, PlayMusicAcivity::class.java)
            if (song_arr != null) {
                if (song_arr!!.size > 0) {
                    intent.putExtra("songs",song_arr as Serializable)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this@Playlist,
                        "Danh sách không có bài hát nào cả :(",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}
