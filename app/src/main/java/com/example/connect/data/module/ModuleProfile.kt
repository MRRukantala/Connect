package com.example.connect.data.module

import com.example.connect.data.api.ProductApiClient
import com.example.connect.data.api.ProfileApiClient
import com.example.connect.data.repository.ProfileRepositoryInteractor
import com.example.connect.data.repository.StoreRepositoryInteractor
import com.example.connect.domain.repo.ProductApiRepository
import com.example.connect.domain.repo.ProfileApiRepository
import com.example.connect.utilites.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ModuleProfile {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ProfileApiClient {
        return retrofit.create(ProfileApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiClient: ProfileApiClient): ProfileApiRepository {
        return ProfileRepositoryInteractor(apiClient)
    }
}