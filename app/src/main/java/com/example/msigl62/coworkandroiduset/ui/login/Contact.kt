package com.example.msigl62.coworkandroiduset.ui.login

interface Contact {
    interface presenter { fun showtext(model: Model, view : Contact.view) }
    interface view { fun contactPresenter(Description:String) }
}