package com.enesselcuk.minibasketapp.di

import com.enesselcuk.minibasketapp.remote.repos.BasketRepositoryImpl
import com.enesselcuk.minibasketapp.remote.service.BasketService
import com.enesselcuk.minibasketapp.repository.BasketRepos
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesRepository(
        api: BasketService,
    ) = BasketRepositoryImpl(api)

}

//@Module
//@InstallIn(ViewModelComponent::class)
//interface Repos {
//    @Binds
//    fun basket(gameRepositoryImpl: BasketRepositoryImpl): BasketRepos
//
//}

