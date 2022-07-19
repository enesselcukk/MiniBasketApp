package com.enesselcuk.minibasketapp.di

import com.enesselcuk.minibasketapp.domain.repository.BasketRepos
import com.enesselcuk.minibasketapp.domain.repository.LocalBasketRepos
import com.enesselcuk.minibasketapp.source.local.BasketDao
import com.enesselcuk.minibasketapp.source.remote.repos.BasketRepositoryImpl
import com.enesselcuk.minibasketapp.source.remote.repos.LocalBasketRepositoryImpl
import com.enesselcuk.minibasketapp.source.remote.service.BasketService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        api: BasketService,
        dispatcher: CoroutineDispatcher,
    ) = BasketRepositoryImpl(api, dispatcher)

    @Provides
    fun providesLocalRepository(
        dao: BasketDao,
        dispatcher: CoroutineDispatcher,
    ) = LocalBasketRepositoryImpl(dao, dispatcher)

}

@Module
@InstallIn(ViewModelComponent::class)
interface Repos {
    @Binds
    fun basket(gameRepositoryImpl: BasketRepositoryImpl): BasketRepos
}

@Module
@InstallIn(ViewModelComponent::class)
interface ReposLocal {
    @Binds
    fun basket(basketLocalRepositoryImpl: LocalBasketRepositoryImpl): LocalBasketRepos

}

