package com.latinos.services.remote.charaters

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class ApiInterceptors {
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor { chain ->
            val defaultRequest = chain.request()

            val defaultHttpUrl = defaultRequest.url

            val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter("apikey", CharactersServiceData.API_KEY)
                .addQueryParameter("ts", CharactersServiceData.timeStamp)
                .addQueryParameter("hash", CharactersServiceData.hash())
                .build()

            val requestBuilder = defaultRequest.newBuilder()
                .url(httpUrl)

            chain.proceed(requestBuilder.build())
        }
}