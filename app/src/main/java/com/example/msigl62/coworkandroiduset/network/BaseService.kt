package com.example.msigl62.coworkandroiduset.network

import com.example.msigl62.coworkandroiduset.model.ResponseData
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BaseService {
    @Multipart
    @POST("upload-image")
    fun sendRequestImage(@Part image: MultipartBody.Part?
    ): Observable<Response<ResponseData>>

    //todo data user
    @POST("register")
    fun requestUploadUserData(@Body name: String?, @Body email: String?, @Body facebook_id: String?
                              , @Body password: String?, @Body image: String?): Observable<Response<ResponseData>>

    //todo data email
    @POST("send-email")
    fun requestSendEmail(@Body id: String?, @Body email: String?
    ): Observable<Response<ResponseData>>


}