package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName


data class ResponseReView(@SerializedName("success") val noticeMessage: String?
                          , @SerializedName("data") val data: List<DataReView>?)

data class DataReView(@SerializedName("name") val name: String?,
                      @SerializedName("date") val date: String?,
                      @SerializedName("comment")val comment: String?)