package com.enesselcuk.minibasketapp.remote.repos


import com.enesselcuk.minibasketapp.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.remote.service.BasketService
import com.enesselcuk.minibasketapp.domain.repository.BasketRepos
import com.enesselcuk.minibasketapp.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val api: BasketService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

) : BasketRepos {
    override suspend fun getProduct(

    ): Flow<NetworkResult<BasketResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val data = api.getStories()
                emit(NetworkResult.Success(data))
            } catch (ex: Throwable) {
                emit(NetworkResult.Error(ex.localizedMessage))
            }
        }.flowOn(dispatcher)


}