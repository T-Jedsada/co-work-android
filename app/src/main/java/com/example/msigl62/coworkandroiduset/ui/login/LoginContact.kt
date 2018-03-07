package com.example.msigl62.coworkandroiduset.ui.login

import com.example.msigl62.coworkandroiduset.model.LoginEmail
import com.example.msigl62.coworkandroiduset.model.LoginFacebook

class LoginContact {
    interface Presenter {
        fun checkEdiTextLogin(model: LoginEmail)
        fun requestValidateApiLogin(model: LoginEmail)
        fun checkIdUserFacebookLogin(model: LoginFacebook)
    }

    interface View {
        fun onSuccessValidated(model: LoginEmail)
        fun onErrorMessage(err: Int)
        fun onResponseFromApiLogin(resMessage: String, name: String?, image: String?,message:String?)
    }
}