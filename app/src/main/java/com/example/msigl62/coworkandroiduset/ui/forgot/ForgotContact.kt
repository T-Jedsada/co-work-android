package com.example.msigl62.coworkandroiduset.ui.forgot

interface ForgotContact {
    interface Presenter {
        fun checkEdiText(email: String)
        fun requestValidateApi(email: String)
    }

    interface View {
        fun onSuccessValidated(email: String)
        fun onErrorMessage(err: Int)
        fun onResponseFromApi(resMessage: String)
    }
}