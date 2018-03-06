package com.example.msigl62.coworkandroiduset.ui.forgot

import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.extension.emailPattern
import com.example.msigl62.coworkandroiduset.model.Forgot

class ForgotPresenter (val view: ForgotContact.View) : ForgotContact.Presenter, InterActor.OnFinishRequest,Request.ForgotListener {

    private val actData: InterActor.ActData = Request()

    override fun requestValidateApi(model: Forgot) {
        actData.requestForgotPassword(model,this)
    }

    override fun onResponseSuccessForgot(user: Forgot?,path: String?) {
        user?.id = path
            if(user?.id.equals(null)){
                view.onResponseFromApi("noSuccess")
            }else{
                user?.let { actData.requestSendEmailForgot(user.id,user.email,this ) }
            }
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
            } }
    }
}