package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.base.BaseContract
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.ui.register.RegisterContactModel
import okhttp3.MultipartBody

class ContractMain {
    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}