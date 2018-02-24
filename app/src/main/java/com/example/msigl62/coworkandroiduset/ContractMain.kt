package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.base.BaseContract

class ContractMain {
    interface View : BaseContract.View
    interface Presenter : BaseContract.Presenter<View>
    interface CallPresenter
}