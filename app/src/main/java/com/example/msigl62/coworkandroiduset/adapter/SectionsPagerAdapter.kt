package com.example.msigl62.coworkandroiduset.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.msigl62.coworkandroiduset.ui.detail.ImageCoWorkPagerOne
import com.example.msigl62.coworkandroiduset.ui.detail.ImageCoWorkPagerTwo

class SectionsPagerAdapter (fm: FragmentManager,t:String) : FragmentPagerAdapter(fm) {
    var text:String=t

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ImageCoWorkPagerOne (text)
        else -> ImageCoWorkPagerTwo (text)
    }

    override fun getCount(): Int=2
}