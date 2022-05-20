package com.example.lastterm.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Adapter.SliderAdapter
import com.example.lastterm.Model.SliderItem
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.example.lastterm.Server.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Frag_Sldier : Fragment() {
    internal lateinit var view: View
    var SliderAdapter: SliderAdapter? = null
    var recyclerViewthinhhanh: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_fragment__slider, container, false)
        recyclerViewthinhhanh = view.findViewById(R.id.recyclerviewthinhhanh)

        GetData()
        return view
    }

    private fun GetData() {
        val dataservice = apiService.getService()
        val callback : Call<List<SongItem>> = dataservice.getSlider()
        callback.enqueue(object : Callback<List<SongItem>> {
            override fun onResponse(
                call: Call<List<SongItem>>,
                response: Response<List<SongItem>>
            ) {
                val mangthinhhanh = response.body() as ArrayList<SongItem>
                SliderAdapter = SliderAdapter(activity!!, mangthinhhanh)
                val linearLayoutManager = LinearLayoutManager(activity)
                linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                recyclerViewthinhhanh!!.layoutManager = linearLayoutManager
                recyclerViewthinhhanh!!.adapter = SliderAdapter


            }

            override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {

            }

        })
    }
}
