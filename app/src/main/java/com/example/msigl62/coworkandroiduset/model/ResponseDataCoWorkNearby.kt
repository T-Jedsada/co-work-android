package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseDataCoWorkNearby(@SerializedName("success") val noticeMessage: String?
                                    , @SerializedName("data") val data: Data?)

data class DataCoWorkNearby(@SerializedName("details") val details: String?,
                @SerializedName("latitude") val latitude: Double?,
                @SerializedName("longitude")val longitude: Double?,
                @SerializedName("name_co-working")val name_CoWork: String?,
                @SerializedName("price_per_hour")val email: String?,
                @SerializedName("status") val status: String?)
