package com.enesselcuk.minibasketapp.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BasketEntity::class], version = 1, exportSchema = false)

abstract class BasketDB : RoomDatabase() {
    abstract fun basketDao(): BasketDao

    companion object {
        @Volatile
        private var INSTANCE: BasketDB? = null

        fun getDatabase(context: Context): BasketDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BasketDB::class.java, "basket_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}