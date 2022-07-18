package com.enesselcuk.minibasketapp.remote.service

import com.enesselcuk.minibasketapp.remote.model.BasketResponse
import retrofit2.http.GET



// https://hepsiemlak.keen4e.repl.co/listing
interface BasketService {
    @GET("stores")
    suspend fun getStories(): BasketResponse




}