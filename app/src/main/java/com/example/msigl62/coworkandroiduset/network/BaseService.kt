package com.example.msigl62.coworkandroiduset.network

import com.example.msigl62.coworkandroiduset.model.ResponseData
import com.example.msigl62.coworkandroiduset.model.ResponseDataForgot
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface BaseService {
    @Multipart
    @POST("upload-image")
    fun sendRequestImage(@Part image: MultipartBody.Part?
    ): Observable<Response<ResponseData>>

    //todo data user
    @FormUrlEncoded
    @POST("register")
    fun requestUploadUserData(@Field("name") name: String?,
                              @Field("email") email: String?,
                              @Field("facebook_id") facebook_id: String?,
                              @Field("password") password: String?,
                              @Field("image") image: String?): Observable<Response<ResponseData>>

    //todo data email
    @FormUrlEncoded
    @POST("send-email/confirm-singup")
    fun requestSendEmail(@Field("id") id: String?, @Field("email") email: String?
    ): Observable<Response<ResponseData>>



    //todo data email
    @FormUrlEncoded
    @POST("forgot-password")
    fun requestForgotEmail(@Field("email") email: String?
    ): Observable<Response<ResponseDataForgot>>



    //todo data email
    @FormUrlEncoded
    @POST("send-email/forgot-password")
    fun requestSendEmailForgot(@Field("id") id: String?, @Field("email") email: String?
    ): Observable<Response<ResponseData>>


}