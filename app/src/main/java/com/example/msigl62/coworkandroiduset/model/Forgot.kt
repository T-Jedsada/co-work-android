package com.example.msigl62.coworkandroiduset.model
import com.google.gson.annotations.SerializedName

data class Forgot(@SerializedName("id") var facebookId: String?,
                  @SerializedName("email") var email: String?)
