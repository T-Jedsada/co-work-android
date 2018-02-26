package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Register(@SerializedName("name") val name: String?
                    , @SerializedName("email") val email: String?
                    , @SerializedName("password") val password: String?
                    , @SerializedName("re_password") val rePassword: String?
                    , @SerializedName("password") val facebookId: String?
                    , @SerializedName("image") val image: MultipartBody.Part?)