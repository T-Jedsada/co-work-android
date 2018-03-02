package com.example.msigl62.coworkandroiduset.ui.forgot

import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.emailPattern
import com.example.msigl62.coworkandroiduset.model.Forgot

class ForgotPresenter (val view: ForgotContact.View) : ForgotContact.Presenter, InterActor.OnFinishRequest,Request.ForgotListener {

    private val actData: InterActor.ActData = Request()


    override fun onResponseSuccessForgot(user: Forgot?) {
        user?.let { actData.requestSendEmailForgot(user.id,user.email,this ) }
    }

    override fun requestValidateApi(model: Forgot) {
        actData.requestForgotPassword(model,this)
    }

    override fun onEmailSuccessForgot(responseData: String?) {
        view.onResponseFromApi("success")
    }


    override fun checkEdiText(model: Forgot) {
        when {
            model.email.isNullOrEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !model.email.emailPattern().matches() -> view.onErrorMessage(R.string.email_format_invalid)
            else -> {
                view.onSuccessValidated(model)
            }
        } }
}