package com.example.msigl62.coworkandroiduset.ui.home

import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.model.modellistcowork.ListCoWorkNearby


class HomePresenter (val view: HomeContact.View) : HomeContact.Presenter,Request.HomeListener {

    private val actData: InterActor.ActData = Request()

    override fun <T> onSuccess(t: T) {
        view.onCallSuccessCoWorkNearby(coWorkNearby = (t as ListCoWorkNearby).results)
    }

    override fun callCoWorkNearby(key: String?) {
        actData.callCoWorkNearby(this)
    }




}