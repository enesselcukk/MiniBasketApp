package com.enesselcuk.minibasketapp.domain.repository

import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.source.remote.model.Body
import com.enesselcuk.minibasketapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BasketRepos {
    suspend fun getProduct(
    ): Flow<NetworkResult<BasketResponse>>

    suspend fun cartProduct(id:Int,amount:Int): Flow<NetworkResult<Body>>
}