package com.example.msigl62.coworkandroiduset.ui.forgot

import android.util.Log
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.extension.emailPattern
import com.example.msigl62.coworkandroiduset.model.ResponseData

class ForgotPresenter(val view: ForgotContact.View) : ForgotContact.Presenter, InterActor.OnFinishRequest, Request.ForgotListener {
    override fun onResponseSuccessForgot(responseData: ResponseData?) {
//        user?.id = path
//        if(user?.id.equals(null)){
//            view.onResponseFromApi("noSuccess")
//        }else{
//            user?.let { actData.requestSendEmailForgot(user.id,user.email,this ) }
//        }
        Log.e("response" , responseData?.data?.idUser)
    }

    private val actData: InterActor.ActData = Request()

    override fun requestValidateApi(email: String) {
        actData.requestForgotPassword(email, this)
    }

    override fun onEmailSuccessForgot(responseData: String?) {
        view.onResponseFromApi("success")
    }

    override fun checkEdiText(email: String) {
        when {
            email.isEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !email.emailPattern().matches() -> view.onErrorMessage(R.string.email_format_invalid)
            else -> {
                view.onSuccessValidated(email)
            }
        }
    }
}