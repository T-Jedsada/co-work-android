package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.network.BaseRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Request : InterActor.ActData {
    override fun requestVerify(user: Register, callback: InterActor.OnFinishRequest) {
        //todo add user.image
        BaseRetrofit.createRx()?.sendRequestVerify(user.facebook_id, user.name, user.email, user.password, user.image)
                ?.enqueue(object : Callback<Register> {
                    override fun onFailure(call: Call<Register>?, t: Throwable?) {}
                    override fun onResponse(call: Call<Register>?, response: Response<Register>?) {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
    }

}