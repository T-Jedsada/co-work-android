package com.example.msigl62.coworkandroiduset.ui.home

import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkNearby


interface HomeContact {
    interface Presenter {

        fun callCoWorkNearby(key: String? = null)
    }

    interface View {
        fun onCallSuccessCoWorkNearby(coWorkNearby: List<CoWorkNearby>?)
    }
}