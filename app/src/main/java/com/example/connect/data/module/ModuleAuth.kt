package com.example.connect.data.module

import com.example.connect.data.api.AuthApiClient
import com.example.connect.data.repository.AuthRespositoryInteractor
import com.example.connect.domain.repo.AuthApiRepository
import com.example.connect.utilites.network.NetworkModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class ModuleAuth {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): AuthApiClient {
        return retrofit.create(AuthApiClient::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiClient: AuthApiClient): AuthApiRepository {
        return AuthRespositoryInteractor(apiClient)
    }
}
