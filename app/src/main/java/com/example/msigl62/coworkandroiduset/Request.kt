package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.network.BaseRx
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class Request : InterActor.ActData {
    override fun requestVerify(user: Register, callback: InterActor.OnFinishRequest) {
        //todo should be crate base Subscribe for clean code and reduce duplicate code NA JA
        BaseRx.createRx()?.sendRequestVerify(user.facebook_id, user.name, user.email, user.password, user.image)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<Register>>() {
                    override fun onComplete() {}

                    override fun onNext(t: Response<Register>) {
                        //todo some thing with data response from service
                    }

                    override fun onError(e: Throwable) {}
                })
    }
}