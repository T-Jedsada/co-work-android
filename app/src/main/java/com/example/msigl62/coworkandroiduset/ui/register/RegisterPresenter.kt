package com.example.msigl62.coworkandroiduset.ui.register
import java.util.regex.Pattern

class RegisterPresenter : RegisterContact.Presenter {
    override fun checkEdiText(model: RegisterContactModel, view: RegisterContact.View) {
        val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val matcher = Pattern.compile(validEmail).matcher(model.edit_email)
        when {
            !matcher.matches() -> view.contactPresenter("Please check email", "", "", "", "", "")
            model.edit_name.isNullOrEmpty()->view.contactPresenter("Please check name", "", "", "", "", "")
            model.edit_email.isNullOrEmpty()->view.contactPresenter("Please check email", "", "", "", "", "")
            model.edit_password.isNullOrEmpty()->view.contactPresenter("Please check password", "", "", "", "", "")
            model.edit_re_password.isNullOrEmpty()->view.contactPresenter("Please check RePassword", "", "", "", "", "")
            model.id_facebook.isNullOrEmpty()->view.contactPresenter("Please check facebook", "", "", "", "", "")
            model.path_image.isNullOrEmpty()->view.contactPresenter("Please check image", "", "", "", "", "")
            !model.edit_re_password.equals(model.edit_password)->view.contactPresenter("Please check RePassword", "", "", "", "", "")
            model.edit_name?.length ?: 0>30->view.contactPresenter("Please check length name", "", "", "", "", "")
            model.edit_password?.length ?: 0>6->view.contactPresenter("Please check length password", "", "", "", "", "")
            model.edit_re_password?.length ?: 0>6->view.contactPresenter("Please check length RePassword", "", "", "", "", "")
            model.path_image.isNullOrEmpty()->view.contactPresenter("Please check No Image", "", "", "", "", "")
            else -> {
                view.contactPresenter("true", model.id_facebook, model.edit_name, model.edit_email, model.edit_password, model.path_image)
            } }
    }
}