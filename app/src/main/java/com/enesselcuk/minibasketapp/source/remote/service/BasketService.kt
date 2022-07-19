package com.enesselcuk.minibasketapp.source.remote.service

import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse
import retrofit2.http.GET


// https://hepsiemlak.keen4e.repl.co/listing
interface BasketService {
    @GET("listing")
    suspend fun getStories(): BasketResponse




}