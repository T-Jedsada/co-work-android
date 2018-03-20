package com.example.msigl62.coworkandroiduset.ui.detail

import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.model.ResponseDetail

class DetailPresenter(val view: DetailContact.View): DetailContact.Presenter, InterActor.OnFinishRequest, Request.DetailCoWorkListener {

    private val actData: InterActor.ActData = Request()

    override fun checkIdProvider(id: String?) {
       actData.callCoWorkDetail(id,this)
    }

    override fun onResponseSuccessDetail(responseDetail: ResponseDetail?) {
        view.onResponseFromApi(responseDetail)
    }
}