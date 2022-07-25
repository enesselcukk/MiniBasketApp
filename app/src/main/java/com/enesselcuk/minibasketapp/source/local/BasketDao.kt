package com.enesselcuk.minibasketapp.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basket")
    suspend fun getAllProduce(): List<BasketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduceFavorite(basket: BasketEntity)

    @Delete
    suspend fun deleteIdProduce(entity: BasketEntity)

}