package com.example.msigl62.coworkandroiduset.callapi

import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.model.*
import com.example.msigl62.coworkandroiduset.model.modellistcowork.ListCoWorkPopular
import com.example.msigl62.coworkandroiduset.network.BaseRetrofit
import com.example.msigl62.coworkandroiduset.network.BaseUrl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody
import retrofit2.Response

class Request : InterActor.ActData {

    interface RegisterListener {
        fun onImageSuccess(user: Register?, path: String?)
        fun onSaveSuccess(responseData: ResponseData?)
        fun onEmailSuccess(responseData: ResponseData?)
    }

    interface ForgotListener {
        fun onResponseSuccessForgot(responseData: ResponseData?)
        fun onEmailSuccessForgot(responseData: String?)
    }

    interface LoginLister {
        fun onResponseSuccessLogin(responseData: String?, name: String?, image: String?, message: String?, status: String?)
        fun onResponseSuccessLoginFacebook(responseData: String?, name: String?, image: String?, status: String?)
    }

    interface HomeListener {
        fun <T> onSuccess(t: T)
        fun onResponseSuccessListCoWorkNearby(responseData: ResponseSuggestion?)
    }

    override fun requestUploadImage(image: MultipartBody.Part, user: Register, callback: RegisterListener) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.sendRequestImage(image)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onImageSuccess(user, it.data?.message) }
                    }

                    override fun onError(e: Throwable) {}
                })
    }

    override fun requestUploadUserData(user: Register, callback: RegisterListener) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.requestUploadUserData(user.name, user.email, user.facebookId, user.password, user.image)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onSaveSuccess(t.body()) }
                    }

                    override fun onError(e: Throwable) {}
                })
    }

    override fun requestSendEmail(id: String?, email: String?, callback: RegisterListener) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.requestSendEmail(id, email)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onEmailSuccess(it) }
                    }

                    override fun onError(e: Throwable) {}
                })
    }

    //TODO login Email
    override fun requestLoginEmail(login: LoginEmail, callback: LoginLister) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.requestLogin(login.email, login.password)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseDataLogin>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseDataLogin>) {
                        t.body()?.let {
                            callback.onResponseSuccessLogin(it.noticeMessage,
                                    it.data?.name, it.data?.image,
                                    it.data?.message, it.data?.status)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    //TODO login Facebook
    override fun requestLoginFacebook(login: LoginFacebook, callback: LoginLister) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.requestLoginFacebook(login.facebookId)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseDataLogin>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseDataLogin>) {
                        t.body()?.let {
                            callback.onResponseSuccessLoginFacebook(it.noticeMessage,
                                    it.data?.name,
                                    it.data?.image,
                                    it.data?.status)
                        }
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    override fun requestForgotPassword(email: String, callback: ForgotListener) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.requestForgotEmail(email)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onResponseSuccessForgot(it) }
                    }

                    override fun onError(e: Throwable) {
                    }
                })
    }

    //TODO send requestSendEmailForgot
    override fun requestSendEmailForgot(id: String?, email: String?, callback: ForgotListener) {
        BaseRetrofit.createRx(BaseUrl.baseUrl)?.requestSendEmailForgot(id, email)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseData>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseData>) {
                        t.body()?.let { callback.onEmailSuccessForgot(it.data?.message) }
                    }

                    override fun onError(e: Throwable) {}
                })
    }

    //TODO listCoWorking
    override fun callCoWorkPopular(callback: HomeListener) {
        val baseService by lazy { BaseRetrofit.createRx(BaseUrl.baseUrl) }
        baseService?.requestCoWorkPopular()?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ListCoWorkPopular>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ListCoWorkPopular>) {
                        t.body()?.let { callback.onSuccess(it) }
                    }

                    override fun onError(e: Throwable) {}
                })
    }

    //TODO listCoWorking CoWorkNearby **
    override fun callCoWorkNearby(longitude: Double, latitude: Double, callback: HomeListener) {
        BaseRetrofit.createRx(BaseUrl.baseUrlSuggest)?.requestCoWorkNearby(longitude,latitude)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(object : DisposableObserver<Response<ResponseSuggestion>>() {
                    override fun onComplete() {}
                    override fun onNext(t: Response<ResponseSuggestion>) {
                        t.body()?.let {callback.onResponseSuccessListCoWorkNearby(it)}
                    }
                    override fun onError(e: Throwable) {
                    }
                })
    }

}