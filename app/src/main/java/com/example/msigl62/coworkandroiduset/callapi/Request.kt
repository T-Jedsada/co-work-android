package com.example.msigl62.coworkandroiduset.callapi

import android.util.Log
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.model.Login
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.network.BaseRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Request : InterActor.ActData {


    override fun requestVerify(user: Register, callback: InterActor.OnFinishRequest) {
        BaseRetrofit.createRx()?.sendRequestVerify(user.facebookId, user.name, user.email, user.password, user.image)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<Register>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<Register>) {
                        t.body()?.let { callback.onSuccess(it) }
                    }
                    override fun onError(e: Throwable) {
                        Log.e("throw wtf ",e.message)
                    }
                })
    }



    //TODO LOGIN
    override fun requestLogin(userLogin: Login, callback: InterActor.OnFinishRequest) {
        BaseRetrofit.createRx()?.sendRequestLogin(userLogin.facebookId,userLogin.email,userLogin.password)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<Login>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<Login>) {
                        t.body()?.let { callback.onSuccess(it) }
                    }
                    override fun onError(e: Throwable) {
                        Log.e("throw wtf ",e.message)
                    }
                })

    }




}