package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseDataForgot(@SerializedName("success") val noticeMessage: String?, @SerializedName("data") val data: DataForgot?)

data class DataForgot(@SerializedName("massage") val message: String?,
                @SerializedName("email") val email: String?,
                @SerializedName("id") val id: String?)
