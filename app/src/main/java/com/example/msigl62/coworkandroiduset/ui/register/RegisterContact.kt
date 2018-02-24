package com.example.msigl62.coworkandroiduset.ui.register
interface RegisterContact {

    interface Presenter {
        fun checkEditex(model: RegisterContactModel, view : View)
    }

    interface View { fun contactPresenter(Description:String) }

}