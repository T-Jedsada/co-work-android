package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.base.BaseContract
import okhttp3.MultipartBody

class ContractMain {
    interface View : BaseContract.View{
        fun callStatusRegister(st:String)
    }


    interface Presenter : BaseContract.Presenter<View>
    interface CallPresenter{
        fun callRegister(name: String,email: String,password: String,facebook_id: String,image: MultipartBody.Part)
    }
}