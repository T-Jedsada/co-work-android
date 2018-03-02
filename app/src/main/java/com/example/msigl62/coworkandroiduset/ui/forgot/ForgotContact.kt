package com.example.msigl62.coworkandroiduset.ui.forgot

import com.example.msigl62.coworkandroiduset.model.Forgot

interface ForgotContact {
    interface Presenter {
        fun checkEdiText(model: Forgot)
        fun requestValidateApi(model : Forgot)
    }
    interface View {
        fun onSuccessValidated(model : Forgot)
        fun onErrorMessage(err : Int)
        fun onResponseFromApi(resMessage : String)
    }
}