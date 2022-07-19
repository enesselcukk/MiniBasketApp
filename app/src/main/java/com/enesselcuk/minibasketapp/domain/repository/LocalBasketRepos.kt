package com.enesselcuk.minibasketapp.domain.repository

import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface LocalBasketRepos {
    suspend fun getSavedBag(): Flow<NetworkResult<List<BasketEntity>>>
}