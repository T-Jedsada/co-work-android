package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.base.BaseContract
import okhttp3.MultipartBody

class ContractMain {
    interface View : BaseContract.View{}
    interface Presenter : BaseContract.Presenter<View>
    interface CallPresenter
}