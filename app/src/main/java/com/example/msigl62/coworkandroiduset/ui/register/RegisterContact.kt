package com.example.msigl62.coworkandroiduset.ui.register

import com.example.msigl62.coworkandroiduset.model.Register
import okhttp3.MultipartBody

interface RegisterContact {

    interface Presenter {
        fun checkEdiText(model: Register)
        fun requestValidateApi(model : Register , image : MultipartBody.Part)
    }
    interface View {
        fun onSuccessValidated(model : Register)
        fun onErrorMessage(err : Int)
        fun onResponseFromApi(resMessage : String)
       }
}