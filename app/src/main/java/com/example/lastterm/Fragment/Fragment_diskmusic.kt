package com.example.lastterm.Fragment

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import com.example.lastterm.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class Fragment_diskmusic : Fragment() {
    internal lateinit var view: View
    var circleImageView: CircleImageView? = null
    internal lateinit var objectAnimator: ObjectAnimator
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_diskmusic_img, container, false)
        circleImageView = view.findViewById(R.id.imageviewcircle)
        objectAnimator = ObjectAnimator.ofFloat(circleImageView, "rotation", 0f, 360f)
        objectAnimator.setDuration(20000)
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE)
        objectAnimator.setRepeatMode(ValueAnimator.RESTART)
        objectAnimator.setInterpolator(LinearInterpolator())
        objectAnimator.start()
        return view
    }

    fun PlayNhac(hinhanh: String?) {
        Picasso.with(activity).load(hinhanh).into(circleImageView)
    }
}