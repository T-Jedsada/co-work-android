package com.example.msigl62.coworkandroiduset.network

import com.example.msigl62.coworkandroiduset.model.Register
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BaseService {
    @Multipart
    @POST("index.php")
    fun sendRequestVerify(@Part("facebook_id") facebook_id: String,
                          @Part("name") name: String,
                          @Part("email") email: String,
                          @Part("password") password: String,
                          @Part image: MultipartBody.Part): Call<Register>
}