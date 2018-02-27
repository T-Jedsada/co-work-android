package com.example.msigl62.coworkandroiduset.callapi

import android.util.Log
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.network.BaseRetrofit
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Request : InterActor.ActData {
    private val gson = Gson()
    override fun requestVerify(user: Register, callback: InterActor.OnFinishRequest) {
        val jsonConverter: String = gson.toJson(user)
        BaseRetrofit.createRx()?.sendRequestVerify(jsonConverter)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<Register>>() {
                    override fun onComplete() {}

                    override fun onNext(t: Response<Register>) {
                        t.body()?.let { callback.onSuccess(it) }
                    }

                    override fun onError(e: Throwable) {
                        Log.e("throw wtf ", e.message)
                    }
                })
    }
}