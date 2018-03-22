package com.example.msigl62.coworkandroiduset.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.msigl62.coworkandroiduset.ui.detail.*

class SectionsPagerAdapter(fm: FragmentManager, imageGalleryOne: String,
                           imageGalleryTwo: String,
                           imageGalleryThree: String,
                           imageGalleryFour: String,
                           imageGalleryFive: String) : FragmentPagerAdapter(fm) {
    private var urlImageOne: String = imageGalleryOne
    private var urlImageTwo: String = imageGalleryTwo
    private var urlImageThree: String = imageGalleryThree
    private var urlImageFour: String = imageGalleryFour
    private var urlImageFive: String = imageGalleryFive
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ImageCoWorkPagerOne(urlImageOne)
        1 -> ImageCoWorkPagerTwo(urlImageTwo)
        2 -> ImageCoWorkPagerThree(urlImageThree)
        3 -> ImageCoWorkPagerFour(urlImageFour)
        else -> ImageCoWorkPagerFive(urlImageFive)
    }

    override fun getCount(): Int = 5
}