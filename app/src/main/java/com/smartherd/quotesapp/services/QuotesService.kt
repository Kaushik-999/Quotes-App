package com.smartherd.quotesapp.services

import com.smartherd.quotesapp.Quotes
import retrofit2.Call
import retrofit2.http.GET

interface QuotesService {

    @GET("/api/quotes")
    fun getQuotes(): Call<ArrayList<Quotes>>

}