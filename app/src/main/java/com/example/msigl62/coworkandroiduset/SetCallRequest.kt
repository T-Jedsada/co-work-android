package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.model.Register
import okhttp3.MultipartBody

class SetCallRequest(val view: ContractMain.View? = null) : ContractMain.CallPresenter, InterActor.OnFinishRequest{

    private val call: InterActor.ActData by lazy { Request() }


    override fun callRegister(name: String, email: String, password: String, facebook_id: String, image: MultipartBody.Part) {
       return call.requestVerify(Register(name.toString(),email.toString(),password.toString(),facebook_id.toString(),image ), this)
    }
    override fun <T> onSuccess(t: T) {view?.callStatusRegister("response")}



}