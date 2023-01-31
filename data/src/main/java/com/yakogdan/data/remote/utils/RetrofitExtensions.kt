package com.yakogdan.data.remote.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitExtensions {

    companion object {
        fun Retrofit.Builder.setClient() = apply {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build()

            this.client(okHttpClient)
        }

        @OptIn(ExperimentalSerializationApi::class)
        fun Retrofit.Builder.addJsonConverter() = apply {
            val json = Json { ignoreUnknownKeys = true }
            val contentType = "application/json".toMediaType()
            this.addConverterFactory(json.asConverterFactory(contentType))
        }
    }
}