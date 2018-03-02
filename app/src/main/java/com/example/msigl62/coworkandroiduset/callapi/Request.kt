package com.example.msigl62.coworkandroiduset.callapi

import android.util.Log
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.model.ResponseData
import com.example.msigl62.coworkandroiduset.network.BaseRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Response

class Request : InterActor.ActData {

    interface RegisterListener {
    fun onImageSuccess(user: Register?, path: String?)
    fun onSaveSuccess(user: Register?)
    fun onEmailSuccess(responseData: String?) }

    override fun requestUploadImage(image: MultipartBody.Part, user: Register, callback: RegisterListener) {
        BaseRetrofit.createRx()?.sendRequestImage(image)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onImageSuccess(user, it.data?.message) }
                    }
                    override fun onError(e: Throwable) {}
                }) }

    override fun requestUploadUserData(user: Register, callback: RegisterListener) {
        BaseRetrofit.createRx()?.requestUploadUserData(user.name, user.email , user.facebookId,user.password, user.image)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onSaveSuccess(user) } }
                    override fun onError(e: Throwable) {}
                })
    }

    override fun requestSendEmail(id :String? ,email :String? , callback: RegisterListener) {
        BaseRetrofit.createRx()?.requestSendEmail(id , email)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onEmailSuccess(it.data?.message) } }
                    override fun onError(e: Throwable) {}
                }) }
}