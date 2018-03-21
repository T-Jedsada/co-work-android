package com.example.msigl62.coworkandroiduset.ui.detail

import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.model.ResponseDetail
import com.example.msigl62.coworkandroiduset.model.ResponseReView

class DetailPresenter(val view: DetailContact.View): DetailContact.Presenter, InterActor.OnFinishRequest, Request.DetailCoWorkListener,Request.DetailReView {

    private val actData: InterActor.ActData = Request()

    override fun checkIdProvider(id: String?) {
        actData.callCoWorkDetail(id,this)
    }

    override fun onResponseSuccessDetail(responseDetail: ResponseDetail?) {
        view.onResponseFromApi(responseDetail)
    }

    override fun checkIdreView(id: String?) {
        actData.callCoWorkDetailReView(id,this)
    }

    override fun onResponseSuccessReView(responseReView: ResponseReView?) {
        view.onResponseFromApiReView(responseReView)
    }

}