package com.enesselcuk.minibasketapp.di

import android.content.Context
import androidx.room.Room
import com.enesselcuk.minibasketapp.source.local.BasketDB
import com.enesselcuk.minibasketapp.source.local.BasketDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): BasketDB {
        return Room.databaseBuilder(context, BasketDB::class.java, "basket")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(
        db: BasketDB,
    ): BasketDao = db.basketDao()

}