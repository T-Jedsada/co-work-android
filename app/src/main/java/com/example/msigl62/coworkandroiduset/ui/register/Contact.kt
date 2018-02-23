package com.example.msigl62.coworkandroiduset.ui.register

interface Contact {
    interface presenter { fun checkMatcherEmail(model: Model, view : view) }
    interface view { fun contactPresenter(Description:String) }
}