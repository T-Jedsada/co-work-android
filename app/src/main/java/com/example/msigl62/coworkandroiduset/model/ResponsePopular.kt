package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponsePopular(@SerializedName("success") val noticeMessage: String?
                              , @SerializedName("data") val data: DataCoWorkPopular?)

data class DataCoWorkPopular(@SerializedName("coworking_id") val details: String?,
                            @SerializedName("provider_id") val latitude: Double?,
                            @SerializedName("poster")val longitude: Double?,
                            @SerializedName("name_co-working")val name_CoWork: String?,
                             @SerializedName("phone")val phone: String?,
                            @SerializedName("rating") val status: String?)