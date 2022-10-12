package com.example.connect.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SaveProductData::class], version = 1, exportSchema = false)
abstract class SavedProductDatabase : RoomDatabase() {

    abstract val savedProductDao: SavedProductDAO

    companion object {

        @Volatile
        private var INSTANCE: SavedProductDatabase? = null

        fun getInstance(context: Context): SavedProductDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SavedProductDatabase::class.java,
                        "saved_product_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}