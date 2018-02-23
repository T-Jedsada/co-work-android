package com.example.msigl62.coworkandroiduset.ui.register
import java.util.regex.Pattern

class RegisterPresenter : RegisterContact.presenter {
    override fun checkMatcherEmail(model: RegisterContactModel, view: RegisterContact.view) {
        val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val matcher = Pattern.compile(validEmail).matcher(model.edittextemail)
        if (matcher.matches()) { view.contactPresenter("True") } else { view.contactPresenter("Fail") }
    }
}