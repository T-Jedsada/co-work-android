package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Register(@SerializedName("facebook_id") var facebookId: String?
                    , @SerializedName("name") var name: String?
                    , @SerializedName("email") var email: String?
                    , @SerializedName("password") var password: String?
                    , @SerializedName("re_password") var rePassword: String?
                    , @SerializedName("image") var image: String?)