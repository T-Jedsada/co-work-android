package com.example.msigl62.coworkandroiduset.ui.home

import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular

interface HomeContact {
    interface Presenter {
        fun callCoWorkNearby(key: String? = null)
    }

    interface View {
        fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?)
    }
}