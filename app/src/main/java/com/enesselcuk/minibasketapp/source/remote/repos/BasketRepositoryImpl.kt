package com.enesselcuk.minibasketapp.source.remote.repos


import com.enesselcuk.minibasketapp.source.remote.model.BasketResponse
import com.enesselcuk.minibasketapp.source.remote.service.BasketService
import com.enesselcuk.minibasketapp.domain.repository.BasketRepos
import com.enesselcuk.minibasketapp.source.remote.model.Body
import com.enesselcuk.minibasketapp.util.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import javax.inject.Inject

class BasketRepositoryImpl @Inject constructor(
    private val api: BasketService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : BasketRepos {
    override suspend fun getProduct(

    ): Flow<NetworkResult<BasketResponse>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val data = api.getStories()
                emit(NetworkResult.Success(data))
            } catch (e: HttpException) {
                emit(NetworkResult.Error(e.message))
            } catch (ex: Throwable) {
                emit(NetworkResult.Error(ex.localizedMessage))
            }
        }.flowOn(dispatcher)

    override suspend fun cartProduct(id:Int,amount:Int): Flow<NetworkResult<Body>> =
        flow {
            emit(NetworkResult.Loading())
            try {
                val data = api.cart(id,amount)
                emit(NetworkResult.Success(data))
            } catch (e: HttpException) {
                emit(NetworkResult.Error(e.message))
            } catch (ex: Throwable) {
                emit(NetworkResult.Error(ex.localizedMessage))
            }

        }.flowOn(dispatcher)


}