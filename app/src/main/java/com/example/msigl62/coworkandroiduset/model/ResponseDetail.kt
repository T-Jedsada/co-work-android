package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseDetail(@SerializedName("success") val noticeMessage: String?
                           , @SerializedName("data") val data: List<DataCoWorkDetail>?)

data class DataCoWorkDetail(@SerializedName("name") val name: String?,
                            @SerializedName("details") val details: String?,
                            @SerializedName("rarting")val rarting: String?,
                            @SerializedName("name_co-working")val name_CoWork: String?,
                            @SerializedName("price_per_hour")val price_per_hour: String?,
                            @SerializedName("address") val address: String?,
                            @SerializedName("latitude") val latitude: Double?,
                            @SerializedName("longitude") val longitude: Double? )
