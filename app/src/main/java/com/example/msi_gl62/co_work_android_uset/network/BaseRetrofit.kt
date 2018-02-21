package com.example.potikorn.testcoopapp.network
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BaseRetrofit {
    companion object Factory {
        fun createRx() : BaseService?{
            val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("").client(setOkHttpClient()).build()
            return retrofit.create(BaseService::class.java) }
        private fun setOkHttpClient(): OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addInterceptor { chain ->
                        val original = chain.request()
                        val originalHttpUrl = original.url()
                        val url = originalHttpUrl.newBuilder()
                                .addQueryParameter("", "")
                                .build()
                        val requestBuilder = original.newBuilder()
                                .url(url)
                        val request = requestBuilder.build()
                        return@addInterceptor chain.proceed(request) }
            return okHttpBuilder.build() } }
}