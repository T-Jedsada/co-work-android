package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ReserveData(@SerializedName("success") val noticeMessage: String?
                       , @SerializedName("data") val data: Data?)