package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.model.Register

class InterActor {
    interface OnFinishRequest {
        fun <T> onSuccess(t:T)
    }

    interface ActData {
        fun requestVerify(user: Register, callback: OnFinishRequest)
    }
}