package com.example.connect.data.module

import com.example.connect.data.api.ApiClient
import com.example.connect.data.repository.RepositoryImplementation
import com.example.connect.domain.repo.ApiRepository
import com.example.connect.utilites.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

class Module {
    @Module(includes = [NetworkModule::class])
    @InstallIn(SingletonComponent::class)
    class ApiModule {
        @Singleton
        @Provides
        fun provideApi(retrofit: Retrofit): ApiClient {
            return retrofit.create(ApiClient::class.java)
        }

        @Singleton
        @Provides
        fun provideRepository(apiClient: ApiClient): ApiRepository {
            return RepositoryImplementation(apiClient)
        }
    }
}