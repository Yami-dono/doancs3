package com.example.lastterm.Fragment


import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lastterm.Adapter.NewupdateAdapter
import com.example.lastterm.Adapter.SingerAdapter
import com.example.lastterm.Model.AlbumItem
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.example.lastterm.Server.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Fragment_Newupdate : Fragment() {
    internal lateinit var view: View
    lateinit var singerAdapter:NewupdateAdapter
    var recyclerViewNgheSi: RecyclerView? = null
    var tenNgheSi: TextView? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_newupdate, container, false)
        recyclerViewNgheSi = view.findViewById(R.id.recyclerview_newupdate)
        tenNgheSi = view.findViewById(R.id.txtnghesi)
        GetData()
        return view
    }

    private fun GetData() {
        val dataservice = apiService.getService()
        val callback = dataservice.getnewupdate()
        callback!!.enqueue(object : Callback<List<SongItem>> {

            override fun onResponse(
                call: Call<List<SongItem>>,
                response: Response<List<SongItem>>,
            ) {
                val singer_Arr = response.body() as ArrayList<SongItem>
                singerAdapter = NewupdateAdapter(activity!!, singer_Arr)
                val linearLayoutManager = LinearLayoutManager(activity)
                linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                recyclerViewNgheSi!!.layoutManager = linearLayoutManager
                recyclerViewNgheSi!!.adapter = singerAdapter
            }



            override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {

            }
        })
    }
}