package com.example.msigl62.coworkandroiduset.ui.register

import android.content.Context
import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.model.Register
import java.util.regex.Pattern

class RegisterPresenter(val view : RegisterContact.View, private val context : Context) : RegisterContact.Presenter {

    override fun checkEdiText(model: Register) {

        val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val matcher = Pattern.compile(validEmail).matcher(model.email)
        when {
            model.name.isNullOrEmpty() ->view.onErrorMessage(R.string.name_empty_message)
            model.email.isNullOrEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !matcher.matches() -> view.onErrorMessage(R.string.email_format_invalid)
            model.password.isNullOrEmpty() -> view.onErrorMessage(R.string.password_empty_massage)
            model.rePassword.isNullOrEmpty() -> view.onErrorMessage(R.string.re_password_empty_massage)
            !model.rePassword.equals(model.password) -> view.onErrorMessage(R.string.invalid_re_password)
            model.name?.length ?: 0 > 30 -> view.onErrorMessage(R.string.name_longer_that_default)
            model.password?.length ?: 0 > 6 -> view.onErrorMessage(R.string.password_longer_that_defaul)
            else -> {
                view.onSuccessValidated(model)
            }
        }
    }
}