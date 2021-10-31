package com.smartherd.quotesapp.services

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    // Base URl
    private const val URL ="https://type.fit"

    // create Interceptor ( used for logging response )
    /*
        levels:
        1. None: No monitoring
        2. Basic: request type, etc (just basic stuffs
        3. Headers: Basic + headers
        4. Body: everything

     */
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // create OkHTTP client
    // interceptor is later added in client by .addInterceptor()
    // run the app and check compiler for logging
    private val okHttp = OkHttpClient.Builder().addInterceptor(logger)

    // create Retrofit Builder
    private val builder  = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // create Retrofit Instance
    private  val retrofit = builder.build()

    // function to return retrofit.create
    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }



}