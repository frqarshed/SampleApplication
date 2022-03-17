package com.sample.data.core.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestApiBuilder(
    private val host: String,
    private val connectTimeout: Long = 30,
    private val readTimeout: Long = 30
) {

    fun build(): Retrofit = Retrofit.Builder()
        .baseUrl(host)
        .client(okHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson()))
        .build()

    private fun okHttpClient() = OkHttpClient.Builder()
        .connectTimeout(connectTimeout, TimeUnit.SECONDS)
        .readTimeout(readTimeout, TimeUnit.SECONDS)
        .build()

    private fun gson() = GsonBuilder()
        .setLenient()
        .enableComplexMapKeySerialization()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()
}