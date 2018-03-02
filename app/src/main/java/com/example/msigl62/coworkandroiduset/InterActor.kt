package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.callapi.Request.RegisterListener
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
    }
}