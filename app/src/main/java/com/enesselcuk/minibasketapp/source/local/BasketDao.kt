package com.enesselcuk.minibasketapp.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    suspend fun getAllProduce(): List<BasketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduceFavorite(basket: BasketEntity)

    @Query("DELETE FROM basket WHERE id = :key")
    suspend fun deleteIdProduce(key: Int?)
}