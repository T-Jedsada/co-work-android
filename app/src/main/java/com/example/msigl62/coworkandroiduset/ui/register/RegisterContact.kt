package com.example.msigl62.coworkandroiduset.ui.register

interface RegisterContact {
    interface presenter { fun checkMatcherEmail(model: RegisterContactModel, view : view) }
    interface view { fun contactPresenter(Description:String) }
}