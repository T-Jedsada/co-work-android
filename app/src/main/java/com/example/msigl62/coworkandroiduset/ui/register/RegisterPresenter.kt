package com.example.msigl62.coworkandroiduset.ui.register
import java.util.regex.Pattern

class RegisterPresenter : RegisterContact.Presenter {
    override fun checkEdiText(model: RegisterContactModel, view: RegisterContact.View) {
        val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val matcher = Pattern.compile(validEmail).matcher(model.edit_email)
        when (matcher.matches()) {
            matcher.matches().equals("") -> view.contactPresenter("Please check", "", "", "", "", "")
            else -> {
                if (matcher.matches().equals("")) {
                    view.contactPresenter("Please check Email", "", "", "", "", "")
                } else {
                    val x: Int? = model.edit_name?.length
                    if (x!! > 30) {
                        view.contactPresenter("Please check Name", "", "", "", "", "")
                    } else {
                        if (model.edit_password.equals("")) {
                            view.contactPresenter("Please check Password", "", "", "", "", "")
                        } else {
                            if (model.edit_password.equals(model.edit_re_password)) {
                                val i: Int? = model.edit_password?.length
                                if (i == 6) {
                                    if (model.path_image.equals(null)) {
                                        view.contactPresenter("NoImage", "", "", "", "", "")
                                    } else {
                                        view.contactPresenter("true", model.id_facebook, model.edit_name, model.edit_email, model.edit_password, model.path_image)
                                    }
                                } else {
                                    view.contactPresenter("Please check length Password", "", "", "", "", "")
                                }
                            } else {
                                view.contactPresenter("Please check RePassword", "", "", "", "", "")
                            }
                        } } } } }

    }





}


