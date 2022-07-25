package com.enesselcuk.minibasketapp.source.remote.service

import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.source.remote.model.Body
import com.enesselcuk.minibasketapp.source.remote.model.BodyResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


// https://hepsiemlak.keen4e.repl.co/listing
interface BasketService {
    @GET("listing")
    suspend fun getStories(): BasketResponse

    @POST("order")
    @FormUrlEncoded
    suspend fun cart(
        @Field("id") id: Int?,
        @Field("amount") amount: Int?,
    ): Body


}