package com.enesselcuk.minibasketapp.source.remote.repos

import com.enesselcuk.minibasketapp.domain.repository.LocalBasketRepos
import com.enesselcuk.minibasketapp.source.local.BasketDao
import com.enesselcuk.minibasketapp.source.local.BasketEntity
import com.enesselcuk.minibasketapp.util.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LocalBasketRepositoryImpl @Inject constructor(
    private val dao: BasketDao,
    private val io: CoroutineContext = Dispatchers.IO
) : LocalBasketRepos {
    override suspend fun getSavedBag(): Flow<NetworkResult<List<BasketEntity>>> = flow {
        emit(NetworkResult.Loading())
        try {
            val data = dao.getAllProduce()
            emit(NetworkResult.Success(data))
        } catch (e: HttpException) {
            emit(NetworkResult.Error(e.message()))
        } catch (ex: Throwable) {
            emit(NetworkResult.Error(ex.localizedMessage))
        }
    }.flowOn(io)


}