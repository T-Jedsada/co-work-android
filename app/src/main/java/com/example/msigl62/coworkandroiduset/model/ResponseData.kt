package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseData(@SerializedName("success") val noticeMessage: String?, @SerializedName("data") val data: Data?)

data class Data(@SerializedName("message") val message: String?)
