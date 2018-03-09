package com.example.msigl62.coworkandroiduset.ui.login

import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.extension.emailPattern
import com.example.msigl62.coworkandroiduset.model.LoginEmail
import com.example.msigl62.coworkandroiduset.model.LoginFacebook

class LoginPresenter(val view: LoginContact.View) : LoginContact.Presenter, InterActor.OnFinishRequest,Request.LoginLister {

    private val actData: InterActor.ActData = Request()

    override fun onResponseSuccessLogin(responseData: String?,name: String?,image: String?,message:String?,status:String?) {
        if(responseData.equals("false")){
            view.onResponseFromApiLogin("false",name,image,message)
        }else{
            if(status.equals("false")){
                view.onResponseFromApiLogin("status-false",name,image,message)
            }else{
                view.onResponseFromApiLogin("success",name,image,message)
            }
        }
    }

    override fun onResponseSuccessLoginFacebook(responseData: String?,name: String?,image: String?,status:String?) {
        if(responseData.equals("false")){
            view.onResponseFromApiLogin("false",name,image,"")
        }else{
            if(status.equals("false")){
                view.onResponseFromApiLogin("status-false",name,image,"")
            }else{
                view.onResponseFromApiLogin("success",name,image,"")
            }
        }
    }

    override fun requestValidateApiLogin(model: LoginEmail) {
        actData.requestLoginEmail(model,this)
    }

    override fun checkIdUserFacebookLogin(model: LoginFacebook) {
        actData.requestLoginFacebook(model,this)
    }

    override fun checkEdiTextLogin(model: LoginEmail) {
        when {
            model.email.isNullOrEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !model.email.emailPattern().matches() -> view.onErrorMessage(R.string.email_format_invalid)
            model.password.isNullOrEmpty() -> view.onErrorMessage(R.string.password_empty_massage)
            model.password?.length ?: 0 > 30 -> view.onErrorMessage(R.string.password_shorter_that_defaul)
            else -> {
                view.onSuccessValidated(model)
            }
        }
    }
}