package com.example.msigl62.coworkandroiduset.ui.detail

import com.example.msigl62.coworkandroiduset.model.ResponseDetail
import com.example.msigl62.coworkandroiduset.model.ResponseReView

class DetailContact {

    interface Presenter {
        fun checkIdProvider(id: String?)
        fun checkIdreView(id: String?)
    }

    interface View {
        fun onResponseFromApi(responseDetail: ResponseDetail?)
        fun onResponseFromApiReView(responseReView: ResponseReView?)
    }
}