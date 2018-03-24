package com.example.msigl62.coworkandroiduset.ui.register

import com.example.msi_gl62.co_work_android_uset.R
import com.example.msigl62.coworkandroiduset.InterActor
import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.extension.emailPattern
import com.example.msigl62.coworkandroiduset.model.Register
import com.example.msigl62.coworkandroiduset.model.ResponseData

class RegisterPresenter(val view: RegisterContact.View) : RegisterContact.Presenter, Request.RegisterListener {
    private val actData: InterActor.ActData = Request()

    override fun onEmailSuccess(responseData: ResponseData?) {
        responseData?.data?.message?.let { view.onResponseFromApi(it) }
    }

    override fun onSaveSuccess(responseData: ResponseData?) {
        if (responseData?.noticeMessage.equals("false")) {
            view.onResponseCheckFromEmail("false", responseData?.data?.messageError)
        } else {
            actData.requestSendEmail(responseData?.data?.id, responseData?.data?.email, this)
        }
    }

    override fun onImageSuccess(user: Register?, path: String?) {
        user?.image = path
        user?.let { actData.requestUploadUserData(it, this) }
    }

    override fun requestValidateApi(model: Register) {
        if (model.image.isNullOrBlank()) {
            actData.requestUploadImage(model.imageFile, model, this)
        } else {
            actData.requestUploadUserData(model, this)
        }
    }

    override fun checkEdiText(model: Register) {
        when {
            model.name.isNullOrEmpty() -> view.onErrorMessage(R.string.name_empty_message)
            model.name?.length ?: 0 > 60 -> view.onErrorMessage(R.string.name_longer_that_default)
            model.email.isNullOrEmpty() -> view.onErrorMessage(R.string.email_empty_massage)
            !model.email.emailPattern().matches() -> view.onErrorMessage(R.string.email_format_invalid)
            model.password.isNullOrEmpty() -> view.onErrorMessage(R.string.password_empty_massage)
            model.password?.length ?: 6 > 30 -> view.onErrorMessage(R.string.password_shorter_that_defaul)
            model.rePassword.isNullOrEmpty() -> view.onErrorMessage(R.string.re_password_empty_massage)
            !model.rePassword.equals(model.password) -> view.onErrorMessage(R.string.invalid_re_password)
            else -> {
                view.onSuccessValidated(model)
            }
        }
    }
}