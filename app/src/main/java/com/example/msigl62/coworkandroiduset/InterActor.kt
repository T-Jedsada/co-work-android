package com.example.msigl62.coworkandroiduset

import com.example.msigl62.coworkandroiduset.model.Register

interface InterActor {
    interface OnFinishRequest {
        //todo some thing with response result here
        fun onSuccess()
    }

    interface ActData {
        fun requestVerify(user: Register, callback: OnFinishRequest)
    }
}