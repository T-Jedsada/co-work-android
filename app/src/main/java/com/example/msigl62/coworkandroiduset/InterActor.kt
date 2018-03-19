package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.callapi.Request
import com.example.msigl62.coworkandroiduset.callapi.Request.RegisterListener
import com.example.msigl62.coworkandroiduset.model.LoginEmail
import com.example.msigl62.coworkandroiduset.model.LoginFacebook
import com.example.msigl62.coworkandroiduset.model.Register
import okhttp3.MultipartBody

class InterActor {
    interface OnFinishRequest {
        fun <T> onSuccess(t: T) {}
    }

    interface ActData {
        fun requestUploadImage(image: MultipartBody.Part, user: Register, callback: RegisterListener)
        fun requestUploadUserData(user: Register, callback: RegisterListener)
        fun requestSendEmail(id: String?, email: String?, callback: RegisterListener)

        fun requestForgotPassword(email : String, callback: Request.ForgotListener)
        fun requestSendEmailForgot(id: String?, email: String?, callback: Request.ForgotListener)

        fun callCoWorkPopular(callback: Request.HomeListener)
        fun callCoWorkNearby(longitude:Double,latitude:Double,callback: Request.HomeListener)

        fun requestLoginEmail(login: LoginEmail, callback: Request.LoginLister)
        fun requestLoginFacebook(login: LoginFacebook,callback: Request.LoginLister)
    }
}