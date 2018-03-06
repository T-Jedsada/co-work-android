package com.example.msigl62.coworkandroiduset.ui.login

import android.util.Log
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.extension.emailPattern
import com.example.msigl62.coworkandroiduset.model.Login

class LoginPresenter(val view: LoginContact.View) : LoginContact.Presenter, InterActor.OnFinishRequest,Request.LoginLister {

    private val actData: InterActor.ActData = Request()

    override fun onResponseSuccessLogin(responseData: String?) {
        Log.e("ResponseSuccessLogin","msq="+responseData)
        view.onResponseFromApi("success")
    }

    override fun requestValidateApi(model: Login) {
         actData.requestLogin(model,this)
    }

    override fun getIdUserFacebookLogin(model: Login) {
        actData.requestLogin(model,this)
    }

    override fun checkEdiTextLogin(model: Login) {
        when {
            model.email.isNullOrEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !model.email.emailPattern().matches() -> view.onErrorMessage(R.string.email_format_invalid)
            model.password.isNullOrEmpty() -> view.onErrorMessage(R.string.password_empty_massage)
            model.password?.length ?: 0 > 30 -> view.onErrorMessage(R.string.password_shorter_that_defaul)
            else -> {
                view.onSuccessValidated(model)
            } }
    }
}