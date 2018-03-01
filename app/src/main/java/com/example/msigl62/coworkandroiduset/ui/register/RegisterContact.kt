package com.example.msigl62.coworkandroiduset.ui.register

import com.example.msigl62.coworkandroiduset.model.Register

interface RegisterContact {

    interface Presenter {
        fun checkEdiText(model: Register)
        fun requestValidateApi(model : Register)
    }
    interface View {
        fun onSuccessValidated(model : Register)
        fun onErrorMessage(err : Int)
        fun onResponseFromApi(resMessage : String)
       }
}