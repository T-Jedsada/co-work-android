package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

class Login (@SerializedName("facebook_id") val facebookId: String?
             , @SerializedName("email") val email: String?
             , @SerializedName("password") val password: String?)
