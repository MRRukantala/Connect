package com.example.connect.data.module

import android.content.Context
import androidx.room.Room
import com.example.connect.data.database.SavedProductDatabase
import com.example.connect.data.repository.KeranjangRepositoryInteractor
import com.example.connect.domain.repo.DatabaseRepository
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton
import dagger.Module
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ModuleDatabase {

    @Singleton // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    fun provideProductDatabase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        SavedProductDatabase::class.java,
        "saved_product_database"
    ).fallbackToDestructiveMigration()
        .build() // The reason we can construct a database for the repo

    @Singleton
    @Provides
    fun provideProductrDao(db: SavedProductDatabase) : DatabaseRepository {
        return KeranjangRepositoryInteractor(db.savedProductDao)
    }

}