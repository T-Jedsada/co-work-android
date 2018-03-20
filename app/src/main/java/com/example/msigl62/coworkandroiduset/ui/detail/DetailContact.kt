package com.example.msigl62.coworkandroiduset.ui.detail

import com.example.msigl62.coworkandroiduset.model.ResponseDetail

class DetailContact {
    interface Presenter {
        fun checkIdProvider(id: String?)
    }

    interface View {
        fun onResponseFromApi(responseDetail: ResponseDetail?)
    }
}