package com.example.connect.data.repository

import com.example.connect.data.api.ApiClient
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.LoginResponse
import com.example.connect.data.model.response.ProfileResponse
import com.example.connect.data.model.response.RegisterResponse
import com.example.connect.domain.entity.SementaraEntity
import com.example.connect.domain.repo.ApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val apiClient: ApiClient
):ApiRepository{
    override suspend fun loginApi(): Flow<Result<SementaraEntity, ResponseObjectWrapper<LoginResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun register(): Flow<Result<SementaraEntity, ResponseObjectWrapper<RegisterResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProfile(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateMyProfile(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun postKiriman(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun postProduct(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAgenda(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun postAgenda(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProductMarkOI(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllProductByAdmin(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductByIdUser(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllLayanan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun postPendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePendidikan(): Flow<Result<SementaraEntity, ResponseObjectWrapper<ProfileResponse>>> {
        TODO("Not yet implemented")
    }
}