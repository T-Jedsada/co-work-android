package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class Register(@SerializedName("name") val name: String
                    , @SerializedName("email") val email: String
                    , @SerializedName("password") val password: String
                    , @SerializedName("password") val facebook_id: String
                    , @SerializedName("image") val image: MultipartBody.Part)