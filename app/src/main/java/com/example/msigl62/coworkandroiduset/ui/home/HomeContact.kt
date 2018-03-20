package com.example.msigl62.coworkandroiduset.ui.home

import com.example.msigl62.coworkandroiduset.model.modellistcowork.CoWorkPopular

import com.example.msigl62.coworkandroiduset.model.ResponseSuggestion

interface HomeContact {
    interface Presenter {
        fun callCoWorkPopular(key: String? = null)
        fun callCoWorkNearby(longitude: Double,latitude: Double)
    }

    interface View {
        fun onCallSuccessCoWorkPopular(coWorkPopular: List<CoWorkPopular>?)
        fun onCallSuccessCoWorkNearby(responseSuggestion: ResponseSuggestion?)
    }
}