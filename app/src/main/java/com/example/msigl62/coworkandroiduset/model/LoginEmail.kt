package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class LoginEmail(@SerializedName("email") val email: String?,
                      @SerializedName("password") val password: String?)
