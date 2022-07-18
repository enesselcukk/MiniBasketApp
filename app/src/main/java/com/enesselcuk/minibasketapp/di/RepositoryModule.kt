package com.enesselcuk.minibasketapp.di

import com.enesselcuk.minibasketapp.domain.repository.BasketRepos
import com.enesselcuk.minibasketapp.remote.repos.BasketRepositoryImpl
import com.enesselcuk.minibasketapp.remote.service.BasketService
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
        dispatcher: CoroutineDispatcher
    ) = BasketRepositoryImpl(api, dispatcher)

}

@Module
@InstallIn(ViewModelComponent::class)
interface Repos {
    @Binds
    fun basket(gameRepositoryImpl: BasketRepositoryImpl): BasketRepos

}

