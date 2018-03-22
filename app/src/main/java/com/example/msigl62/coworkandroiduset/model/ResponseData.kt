package com.example.msigl62.coworkandroiduset.model

import com.google.gson.annotations.SerializedName

data class ResponseData(@SerializedName("success") val noticeMessage: String?
                        , @SerializedName("data") val data: Data?)

//TODO  _id is the same as id because we need to hanble api response need to fix later when api was fixed
data class Data(@SerializedName("message") val message: String?,
                @SerializedName("error") val messageError: String?,
                @SerializedName("_id")val id: String?,
                @SerializedName("id")val idUser: String?,
                @SerializedName("email")val email: String?,
                @SerializedName("status") val status: String?)