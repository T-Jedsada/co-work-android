package com.example.msigl62.coworkandroiduset.ui.register

import com.example.msigl62.coworkandroiduset.model.Register

interface RegisterContact {

    interface Presenter {
        fun checkEdiText(model: Register)
    }
    interface View {
        fun callStatusRegister(model : Register)
        fun onSuccessValidated(model : Register)
        fun onErrorMessage(err : Int)
       }
}