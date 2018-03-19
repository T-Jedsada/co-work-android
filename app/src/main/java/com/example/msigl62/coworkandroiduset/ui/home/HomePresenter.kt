package com.example.msigl62.coworkandroiduset.ui.home

import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.model.modellistcowork.ResponseSuggestion
import com.example.msigl62.coworkandroiduset.model.modellistcowork.ListCoWorkPopular

class HomePresenter (val view: HomeContact.View) : HomeContact.Presenter,Request.HomeListener {

    private val actData: InterActor.ActData = Request()

    override fun <T> onSuccess(t: T) {
        view.onCallSuccessCoWorkPopular(coWorkPopular = (t as ListCoWorkPopular).results)
    }

    override fun callCoWorkPopular(key: String?) {
        actData.callCoWorkPopular(this)
    }


    override fun onResponseSuccessListCoWorkNearby(responseData: ResponseSuggestion?) {
      //TODO Request api CoWorkNearby **
    }

    override fun callCoWorkNearby(longitude: Double, latitude: Double) = actData.callCoWorkNearby(longitude , latitude,this)
}