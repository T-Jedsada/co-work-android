package com.example.msigl62.coworkandroiduset.network

import com.example.msigl62.coworkandroiduset.model.Register
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface BaseService {
    @Multipart
    @POST("index.php")
    fun sendRequestVerify(@Part("user") userData: String): Observable<Response<Register>>
}