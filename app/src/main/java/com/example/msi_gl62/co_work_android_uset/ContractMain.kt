package com.example.msi_gl62.rider
import com.example.msi_gl62.buildvariantsandroid.base.BaseContract

class ContractMain {
    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>

    interface CallPresenter
}