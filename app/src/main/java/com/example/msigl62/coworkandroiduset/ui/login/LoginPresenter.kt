package com.example.msigl62.coworkandroiduset.ui.login

import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.model.Login
import java.util.regex.Pattern


class LoginPresenter(val view: LoginContact.View) : LoginContact.Presenter, InterActor.OnFinishRequest {


    private val actData: InterActor.ActData = Request()

    override fun <T> onSuccess(t: T) {
        view.onResponseFromApi("success")
    }

    override fun requestValidateApi(model: Login) {

    }

    override fun checkEdiTextLogin(model: Login) {
        val validEmail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+"
        val matcher = Pattern.compile(validEmail).matcher(model.email)
        when {
            model.email.isNullOrEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !matcher.matches() -> view.onErrorMessage(R.string.email_format_invalid)
            model.password.isNullOrEmpty() -> view.onErrorMessage(R.string.password_empty_massage)
            model.password?.length ?: 0 > 6 -> view.onErrorMessage(R.string.password_longer_that_defaul)
            else -> {
                view.onSuccessValidated(model)
            }
        }
    }


}