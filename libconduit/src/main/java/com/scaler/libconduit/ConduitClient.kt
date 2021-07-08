package com.scaler.libconduit

import com.scaler.libconduit.apis.ConduitApi
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ConduitClient {
    private val okHttpClient by lazy {
        OkHttpClient.Builder()
        .addNetworkInterceptor(loggingIntercepter)
        .build()
    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val loggingIntercepter= HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)



    val api = retrofit.create(ConduitApi::class.java)
}