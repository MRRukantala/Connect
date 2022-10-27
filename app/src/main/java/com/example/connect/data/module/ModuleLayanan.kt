package com.example.connect.data.module

import com.example.connect.data.api.LayananApiClient
import com.example.connect.data.api.ProductApiClient
import com.example.connect.data.repository.LayananRepositoryInteractor
import com.example.connect.data.repository.StoreRepositoryInteractor
import com.example.connect.domain.repo.LayananApiRepository
import com.example.connect.domain.repo.ProductApiRepository
import com.example.connect.utilites.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ModuleLayanan {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): LayananApiClient {
        return retrofit.create(LayananApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiClient: LayananApiClient): LayananApiRepository {
        return LayananRepositoryInteractor(apiClient)
    }
}