package com.example.msigl62.coworkandroiduset.ui.home

import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular

interface HomeContact {
    interface Presenter {
        fun callCoWorkPopular(key: String? = null)
        fun callCoWorkNearby(longtitude: Double,latitude: Double)
    }

    interface View {
        fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?)
        fun onCallSuccessCoWorkNearby()
    }
}