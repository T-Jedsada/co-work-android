package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseSuggestion(@SerializedName("success") val noticeMessage: String?
                              , @SerializedName("data") val data: List<DataCoWorkNearby>?)

data class DataCoWorkNearby(@SerializedName("coworking_id") val id: String,
                            @SerializedName("name_co-working") val name: String,
                            @SerializedName("provider_id") val providerId: String,
                            @SerializedName("status") val status: String,
                            @SerializedName("average_rating") val averageRating: String)