package com.example.lastterm.Fragment

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lastterm.Adapter.SearchAdapter
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.example.lastterm.Server.apiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class Fragment_search : Fragment() {
    internal lateinit var view: View
    var toolbar: Toolbar? = null
    var recyclerViewtim: RecyclerView? = null
    var textViewnull: TextView? = null
    var textViewnnotfound: TextView? = null
    var timKiemAdapter: SearchAdapter? = null
    var mangbaihat: ArrayList<SongItem>?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_search, container, false)
        toolbar = view.findViewById(R.id.toilbartimkiem)
        recyclerViewtim = view.findViewById(R.id.recyclerviewtimkiem)
        textViewnull = view.findViewById(R.id.textviewtimkiemnull)
        textViewnnotfound = view.findViewById(R.id.textviewtimkiemnotfound)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_timkiem, menu)
        val menuItem = menu.findItem(R.id.menutimkiem)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                recyclerViewtim!!.setBackgroundColor(Color.BLACK)
                if (s.trim { it <= ' ' } != "") {
                    TimKiemBaiHat(s)
                }
                else
                    recyclerViewtim!!.visibility = View.GONE


                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun TimKiemBaiHat(keyword: String) {
        val dataservice = apiService.getService()
        val callback = dataservice.GetSearchSong(keyword)
        callback!!.enqueue(object : Callback<List<SongItem>> {

            override fun onResponse(
                call: Call<List<SongItem>>,
                response: Response<List<SongItem>>,
            ) {
                mangbaihat = response.body() as ArrayList<SongItem>
                if (mangbaihat!!.size > 0) {
                    timKiemAdapter = SearchAdapter(activity!!, mangbaihat!!)
                    val linearLayoutManager = LinearLayoutManager(activity)
                    recyclerViewtim!!.layoutManager = linearLayoutManager
                    recyclerViewtim!!.adapter = timKiemAdapter
                    textViewnull!!.visibility = View.GONE
                    recyclerViewtim!!.visibility = View.VISIBLE
                } else {
                    recyclerViewtim!!.visibility = View.GONE
                    textViewnull!!.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<List<SongItem>>, t: Throwable) {

            }
        })
    }
}