package com.example.lastterm.Activity


import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.lastterm.Adapter.PagerPlaymusic
import com.example.lastterm.Fragment.Fragment_diskmusic
import com.example.lastterm.Model.SongItem
import com.example.lastterm.R
import com.example.lastterm.databinding.ActivityPlaymusicBinding
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class PlayMusicAcivity :AppCompatActivity() {
    var baihat: SongItem? = null
    internal lateinit var toolbarplaynhac: Toolbar
    lateinit var seekBarnhac: SeekBar
    lateinit var imageViewtim: ImageView
    lateinit var textViewtennhac: TextView
    lateinit var textViewcasi: TextView
    lateinit var textViewrunrime: TextView
    lateinit var textViewtatoltime: TextView
    lateinit var imageButtontronnhac: ImageButton
    lateinit var imageButtonpreviewnhac: ImageButton
    lateinit var imageButtonplaypausenhac: ImageButton
    lateinit var imageButtonnexnhac: ImageButton
    lateinit var imageButtonlapnhac: ImageButton
    lateinit var fragment_diskmusic: Fragment_diskmusic
    lateinit var adapternhac: PagerPlaymusic
  private var mediaplayer:MediaPlayer?=null
    private var index = 0
    private var position = 0
    private var repeat = false
    private var checkrandom = false
    private var next = false




    var mangbaihat: ArrayList<SongItem> = ArrayList()
    private lateinit var binding: ActivityPlaymusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        binding = ActivityPlaymusicBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Getdata()
        innit()
        eventclick()



    }

    private fun eventclick() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if(mangbaihat!!.size==1)
                {
                    repeat=true
                    checkrandom=false
                    imageButtonlapnhac!!.setImageResource(R.drawable.iconrepeat)
                    imageButtontronnhac!!.setImageResource(R.drawable.iconshuffled)
                    fragment_diskmusic!!.PlayNhac(mangbaihat!![position].songImg)
                }



                if (mangbaihat!!.size > 0) {
                    fragment_diskmusic.PlayNhac(mangbaihat[position].songImg)
                    handler.removeCallbacks(this)

                }
                else
                    handler.postDelayed(this,1000)

        }}, 1000)

        imageButtonplaypausenhac.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
               if(mediaplayer!!.isPlaying)
               {
                   mediaplayer!!.pause()
                   imageButtonplaypausenhac!!.setImageResource(R.drawable.nutpause)
               }
                else
               {
                   mediaplayer!!.start()
                   imageButtonplaypausenhac!!.setImageResource(R.drawable.nutplay)

               }
            }

        })
        imageButtonlapnhac!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if(mangbaihat!!.size==1)
                {
                   if (repeat==false)
                   {
                       repeat=true
                       imageButtonlapnhac.setImageResource(R.drawable.iconrepeat)
                       Toast.makeText(this@PlayMusicAcivity,"dang lap bai ",Toast.LENGTH_LONG).show()


                   }
                    else{
                        repeat=false
                       imageButtonlapnhac.setImageResource(R.drawable.iconsyned)
                       Toast.makeText(this@PlayMusicAcivity,"ko lap bai ",Toast.LENGTH_LONG).show()

                    }
                }
                else if(repeat==false)
                {
                  if(checkrandom==true)
                  {
                      checkrandom=false
                      repeat=true

                      imageButtonlapnhac.setImageResource(R.drawable.iconrepeat)
                      imageButtontronnhac.setImageResource(R.drawable.iconshuffled)
                  }
                    imageButtonlapnhac.setImageResource(R.drawable.iconrepeat)
                    repeat=true
                }
                else if(repeat==true)
                {
                    imageButtonlapnhac.setImageResource(R.drawable.iconsyned)

                    repeat=false

                }
            }

        })
        imageButtontronnhac.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if(mangbaihat!!.size==1)
                {
                    Toast.makeText(this@PlayMusicAcivity,"Bạn đang phát 1 bài hát không thể trộn bài ",Toast.LENGTH_LONG).show()
                }
                else if(checkrandom==false)
                {
                    if(repeat==true)
                    {
                        repeat=false
                        checkrandom=true
                        imageButtontronnhac.setImageResource(R.drawable.iconsuffle)
                        imageButtonlapnhac.setImageResource(R.drawable.iconsyned)
                    }
                    imageButtontronnhac.setImageResource(R.drawable.iconsuffle)
                    checkrandom=true
                }
                else if(checkrandom==true)
                {
                    imageButtontronnhac.setImageResource(R.drawable.iconshuffled)

                    checkrandom=false



                }
            }

        })
        seekBarnhac.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar,
                                           progress: Int, fromUser: Boolean) {
                // write custom code for progress is changed
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
              mediaplayer!!.seekTo(seekBarnhac.progress)
            }
        })
        imageButtonnexnhac.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if(mangbaihat!!.size>0)
                {
                   if(mediaplayer!!.isPlaying || mediaplayer !=null)
                   {
                       mediaplayer!!.stop()
                       mediaplayer!!.release()
                       mediaplayer=null

                   }
                    if(position< mangbaihat!!.size)
                    {

                        position++
                        if(repeat==true)
                        {
                            if(position==0)
                            {
                                position= mangbaihat!!.size
                            }
                            position-=1

                        }
                        if(checkrandom==true)
                        {
                            var random:Random= Random()
                            index= random.nextInt(mangbaihat!!.size)
                            if(index==position) {
                                while (index == position) {
                                    var random: Random = Random()
                                    index = random.nextInt(mangbaihat!!.size)
                                }
                            }
                            else
                                position=index

                        }
                        if (position>mangbaihat!!.size-1)
                        {
                            position=0
                        }



                        playmusic().execute(mangbaihat!![position].songurl)
                        fragment_diskmusic.PlayNhac(mangbaihat!![position].songImg)
                        imageButtonplaypausenhac.setImageResource(R.drawable.nutplay)
                        supportActionBar!!.setTitle(mangbaihat!![position].songname)
                        textViewtennhac.setText(mangbaihat!![position].songname)
                        textViewcasi.setText(mangbaihat!![position].singer)

                    }
                }

                }
                   })
        imageButtonpreviewnhac.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                if(mangbaihat!!.size>0)
                {
                    if(mediaplayer!!.isPlaying || mediaplayer !=null)
                    {
                        mediaplayer!!.stop()
                        mediaplayer!!.release()
                        mediaplayer=null
                    }
                    if(position< mangbaihat!!.size)
                    {

                        position--
                        if(position<0)
                        {
                            position= mangbaihat!!.size - 1
                        }
                        if(repeat==true)
                        {
                            if(position==0)
                            {
                                position= mangbaihat!!.size
                            }
                            position+=1

                        }
                        if(checkrandom==true)
                        {
                            var random:Random= Random()
                            index= random.nextInt(mangbaihat!!.size)
                            if(index==position) {
                                while (index == position) {
                                    var random = Random()
                                    index = random.nextInt(mangbaihat!!.size)
                                }
                            }
                            else
                            position=index

                        }



                        playmusic().execute(mangbaihat!![position].songurl)
                        fragment_diskmusic!!.PlayNhac(mangbaihat!![position].songImg)
                        supportActionBar!!.setTitle(mangbaihat!![position].songname)
                        textViewtennhac!!.setText(mangbaihat!![position].songname)
                        textViewcasi!!.setText(mangbaihat!![position].singer)

                    }
                }



            }})
    }


    private fun innit() {
        toolbarplaynhac = binding.toolbarplaynhac
        seekBarnhac = binding.seekBartime
        val viewPagerplaynhac = binding.viewPagerdianhac
        imageViewtim = binding.imageViewtimplaynhac
        imageButtontronnhac = binding.imageButtontron
        imageButtonpreviewnhac = binding.imageButtonpreview
        imageButtonplaypausenhac = binding.imageButtonplaypause
        imageButtonnexnhac = binding.imageButtonnext
        imageButtonlapnhac = binding.imageButtonlap
        textViewtatoltime = binding.textViewtimetotal
        textViewcasi = binding.textViewtencasiplaynhac
        textViewtennhac = binding.textViewtenbaihatplaynhac
        textViewrunrime = binding.textViewruntime
        fragment_diskmusic = Fragment_diskmusic()
        adapternhac = PagerPlaymusic(supportFragmentManager)
        adapternhac!!.AddFragment(fragment_diskmusic!!)

        viewPagerplaynhac.adapter = adapternhac
        setSupportActionBar(toolbarplaynhac)
        Objects.requireNonNull(supportActionBar)!!.setDisplayHomeAsUpEnabled(true)
        toolbarplaynhac.setNavigationOnClickListener(View.OnClickListener {
            finish()
        mediaplayer!!.stop()
            mangbaihat!!.clear()

        })

        fragment_diskmusic = adapternhac!!.getItem(position) as Fragment_diskmusic
        if(mangbaihat!!.size>0)
        {
            supportActionBar!!.setTitle(mangbaihat!![0].songname)
            textViewtennhac!!.setText(mangbaihat!![0].songname)
            textViewcasi!!.setText(mangbaihat!![0].singer)
            playmusic().execute(mangbaihat!![0].songurl)


        }
    }

    private fun Getdata() {
        val intent: Intent = getIntent()
        mangbaihat!!.clear()
        if(intent.hasExtra("song")) {
           baihat = intent.getSerializableExtra("song") as SongItem?
            mangbaihat!!.add(baihat!!)



        }
        if(intent.hasExtra("songs")) {
            mangbaihat = intent.getSerializableExtra("songs") as ArrayList<SongItem>




        }
    }


    internal inner class playmusic : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg p0: String?): String {
            return p0[0]!!
        }

        override fun onPostExecute(baihat: String) {
            super.onPostExecute(baihat)
            try {
                mediaplayer = MediaPlayer()
                mediaplayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaplayer!!.setOnCompletionListener {
                    mediaplayer!!.stop()
                    mediaplayer!!.reset()
                }
                mediaplayer!!.setDataSource(baihat)
                mediaplayer!!.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mediaplayer!!.start()
            Thread.sleep(500)
            Timesong()
            UpdateTimesong()
        }


    }

    private fun UpdateTimesong() {
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                if(mediaplayer!=null)
                {
                    seekBarnhac!!.setProgress(mediaplayer!!.currentPosition)
                  var simpledateformart =SimpleDateFormat("mm:ss")
                    textViewrunrime!!.setText(simpledateformart.format(mediaplayer!!.currentPosition))
                }
                handler.postDelayed(this, 600)

                mediaplayer!!.setOnCompletionListener{
                    if (checkrandom==false && repeat==false&& mangbaihat!!.size==1)
                    {
                        mediaplayer!!.pause()
                        imageButtonplaypausenhac!!.setImageResource(R.drawable.nutpause)
                    }


                    else
                    next=true
                        try {
                            Thread.sleep(1500)
                        }catch (e: InterruptedException){
                            e.printStackTrace()
                        }


                }
            }
        }, 600)
        val handler1 = Handler()
        handler1.postDelayed(object : Runnable {
            override fun run() {
                if(next==true)
                {
                    if(mangbaihat!!.size==1)
                    {
                        if(repeat==false) {
                            playmusic().execute(mangbaihat!![position].songurl)

                            fragment_diskmusic!!.PlayNhac(mangbaihat!![position].songImg)
                            supportActionBar!!.setTitle(mangbaihat!![position].songname)
                            textViewtennhac!!.setText(mangbaihat!![position].songname)
                            textViewcasi!!.setText(mangbaihat!![position].singer)
                        }

                        if(repeat==true)
                        {
                            position++

                            if(position==0)
                            {
                                position= mangbaihat!!.size
                            }
                            position-=1
                            playmusic().execute(mangbaihat!![position].songurl)
                            fragment_diskmusic!!.PlayNhac(mangbaihat!![position].songImg)
                            supportActionBar!!.setTitle(mangbaihat!![position].songname)
                            textViewtennhac!!.setText(mangbaihat!![position].songname)
                            textViewcasi!!.setText(mangbaihat!![position].singer)
                        }

                    }
                   else if(position< mangbaihat!!.size)
                    {

                        position++

                        if(repeat==true)
                        {
                            if(position==0)
                            {
                                position= mangbaihat!!.size
                            }
                            position-=1

                        }
                        if(checkrandom==true)
                        {
                            var random:Random= Random()
                            index= random.nextInt(mangbaihat!!.size)
                            if(index==position) {
                                while (index == position) {
                                    var random: Random = Random()
                                    index = random.nextInt(mangbaihat!!.size)
                                }
                            }
                            else
                                position=index

                        }
                        if (position>mangbaihat!!.size-1)
                        {
                            position=0

                        }


                        playmusic().execute(mangbaihat!![position].songurl)
                        fragment_diskmusic!!.PlayNhac(mangbaihat!![position].songImg)
                        supportActionBar!!.setTitle(mangbaihat!![position].songname)
                        textViewtennhac!!.setText(mangbaihat!![position].songname)
                        textViewcasi!!.setText(mangbaihat!![position].singer)
                    }

                    next=false
                    handler1.removeCallbacks(this)
                }
                else
                    handler1.postDelayed(this,1200)

            }},1200)
    }

    private fun Timesong() {
        val simpleDateFormat = SimpleDateFormat("mm:ss")
        textViewtatoltime!!.text = simpleDateFormat.format(mediaplayer!!.duration)
        seekBarnhac!!.max = mediaplayer!!.duration
    }

}