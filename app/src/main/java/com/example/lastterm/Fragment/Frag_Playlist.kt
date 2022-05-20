package com.example.lastterm.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Adapter.PlaylistAdapter
import com.example.lastterm.Adapter.SliderAdapter
import com.example.lastterm.Model.PlaylistItem
import com.example.lastterm.Model.SliderItem
import com.example.lastterm.R
import com.example.lastterm.Server.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Frag_Playlist : Fragment() {
    internal lateinit var view: View
    var playlistadapter: PlaylistAdapter? = null
    var recyclerView_Playlist: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_playlist, container, false)
        recyclerView_Playlist = view.findViewById(R.id.recyclerview_playlist)

        GetData()
        return view
    }

    private fun GetData() {
        val dataservice = apiService.getService()
        val callback : Call<List<PlaylistItem>> = dataservice.getPlaylist()
        callback.enqueue(object : Callback<List<PlaylistItem>> {

            override fun onResponse(
                call: Call<List<PlaylistItem>>,
                response: Response<List<PlaylistItem>>
            ) {
                val mangplaylist = response.body() as ArrayList<PlaylistItem>
                playlistadapter = PlaylistAdapter(activity!!, mangplaylist)
                val linearLayoutManager = LinearLayoutManager(activity)
                linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                recyclerView_Playlist!!.layoutManager = linearLayoutManager
                recyclerView_Playlist!!.adapter = playlistadapter


            }

            override fun onFailure(call: Call<List<PlaylistItem>>, t: Throwable) {

            }

        })
    }
}
