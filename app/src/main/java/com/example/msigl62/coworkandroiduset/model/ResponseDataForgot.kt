package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseDataForgot(@SerializedName("success") val noticeMessage: String?,
                              @SerializedName("data") val data: DataForgot?)

data class DataForgot(@SerializedName("message") val message: String?,
                @SerializedName("email") val email: String?,
                @SerializedName("_id") val id: String?)
