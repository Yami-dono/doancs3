package com.example.lastterm.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.lastterm.Adapter.MainViewPagerAdapter
import com.example.lastterm.Fragment.Fragment_home
import com.example.lastterm.Fragment.Fragment_search
import com.example.lastterm.R
import com.example.lastterm.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private var tabLayout: TabLayout?= null
    private var viewPager: ViewPager? = null

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setTheme(R.style.splash_screen)
        setContentView(view)
        AnhXa()
        init()

    }
    private fun init() {
        val mainViewPagerAdapter = MainViewPagerAdapter(supportFragmentManager)
        mainViewPagerAdapter.addFragment(Fragment_home(),"" )
        mainViewPagerAdapter.addFragment(Fragment_search(), "")

        viewPager!!.adapter = mainViewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
        tabLayout!!.getTabAt(0)!!.setIcon(R.drawable.home)
        tabLayout!!.getTabAt(1)!!.setIcon(R.drawable.iconsearch)

    }

    private fun AnhXa() {
        tabLayout = binding.myTabLayout
        viewPager =  binding.myViewPager

    }
}
