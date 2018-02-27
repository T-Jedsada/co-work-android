package com.example.msigl62.coworkandroiduset.ui.login

import com.example.msigl62.coworkandroiduset.model.Login

class LoginContact {
    interface Presenter {
        fun checkEdiTextLogin(model: Login)
        fun requestValidateApi(model : Login)
    }
    interface View {
        fun onSuccessValidated(model : Login)
        fun onErrorMessage(err : Int)
        fun onResponseFromApi(resMessage : String)
    }
}