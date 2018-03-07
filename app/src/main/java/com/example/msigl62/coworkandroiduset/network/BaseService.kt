package com.example.msigl62.coworkandroiduset.network

import com.example.msigl62.coworkandroiduset.model.ResponseData
import com.example.msigl62.coworkandroiduset.model.ResponseDataForgot
import com.example.msigl62.coworkandroiduset.model.ResponseDataLogin
import com.example.msigl62.coworkandroiduset.model.modellistcowork.ListCoWorkNearby
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface BaseService {
    @Multipart
    @POST("upload-image")
    fun sendRequestImage(@Part image: MultipartBody.Part?
    ): Observable<Response<ResponseData>>

    @FormUrlEncoded
    @POST("register")
    fun requestUploadUserData(@Field("name") name: String?,
                              @Field("email") email: String?,
                              @Field("facebook_id") facebook_id: String?,
                              @Field("password") password: String?,
                              @Field("image") image: String?): Observable<Response<ResponseData>>

    @FormUrlEncoded
    @POST("send-email/confirm-singup")
    fun requestSendEmail(@Field("id") id: String?,
                         @Field("email") email: String?
    ): Observable<Response<ResponseData>>

    @FormUrlEncoded
    @POST("register/forgot-password")
    fun requestForgotEmail(@Field("email") email: String?
    ): Observable<Response<ResponseData>>

    @FormUrlEncoded
    @POST("send-email/forgot-password")
    fun requestSendEmailForgot(@Field("id") id: String?,
                               @Field("email") email: String?
    ): Observable<Response<ResponseData>>

    //TODO login email **
    @FormUrlEncoded
    @POST("email-login")
    fun requestLogin(@Field("email") email: String?,
                     @Field("password") password: String?
    ): Observable<Response<ResponseDataLogin>>

    //TODO login facebook**
    @FormUrlEncoded
    @POST("facebook-login")
    fun requestLoginFacebook(@Field("facebook_id") facebook_id: String?
    ): Observable<Response<ResponseDataLogin>>


    //TODO listcowork
    @GET("api/blog")
    fun requestCoWorkNearby(): Observable<Response<ListCoWorkNearby>>


}