package com.enesselcuk.minibasketapp.domain.repository

import com.enesselcuk.minibasketapp.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface BasketRepos {
    suspend fun getProduct(
    ): Flow<NetworkResult<BasketResponse>>
}