package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.AgendaRequest
import com.example.connect.data.model.request.LoginRequest
import com.example.connect.data.model.response.*
import com.example.connect.domain.entity.*
import com.example.connect.domain.repo.ApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UseCase @Inject constructor(
    private val repository: ApiRepository
) {
    suspend fun login(loginRequest: LoginRequest):
            Flow<Result<LoginEntity, ResponseObjectWrapperSementara<LoginResponse>>> {
        return repository.loginApi(loginRequest)
    }

    suspend fun getAllAgenda():
            Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return repository.getAllAgenda()
    }

    suspend fun getAgendaByIdUser(id: Int):
            Flow<Result<List<AgendaEntity>, ResponseListWrapper<AgendaResponse>>> {
        return repository.getAgendaByIdUser(id)
    }

    suspend fun getDetailAgenda(id: Int):
            Flow<Result<AgendaEntity, ResponseObjectWrapper<AgendaResponse>>> {
        return repository.getDetailAgenda(id)
    }

    suspend fun getAllKiriman():
    Flow<Result<List<KirimanEntity>, ResponseListWrapperSementara<KirimanResponse>>>{
        return repository.getAllKiriman()
    }

    suspend fun getKirimanByIdUser(id:Int):
    Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>>{
        return repository.getKirimanByIdUser(id)
    }

    suspend fun getDetailKiriman(id:Int):
    Flow<Result<KirimanEntity, ResponseObjectWrapper<KirimanResponse>>>{
        return repository.getDetailKiriman(id)
    }

    suspend fun getLayanan():
    Flow<Result<List<LayananEntity>, ResponseListWrapper<LayananResponse>>>{
        return repository.getAllLayanan()
    }

    suspend fun getDetailLayanan(id:Int):
    Flow<Result<LayananEntity, ResponseObjectWrapper<LayananResponse>>>{
        return repository.getDetailLayanan(id)
    }

    suspend fun getProfile(id:Int):
    Flow<Result<ProfileEntity, ResponseObjectWrapper<ProfileResponse>>>{
        return repository.getProfile(id)
    }

    suspend fun getProductByAdmin(id:Int):
    Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>{
        return repository.getAllProductByAdmin(id)
    }

    suspend fun getAllProduct():
    Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>{
        return repository.getAllProductMarkOI()
    }

    suspend fun getAllProductByUser(id:Int):
    Flow<Result<List<ProductEntity>, ResponseListWrapper<ProductResponse>>>{
        return repository.getProductByIdUser(id)
    }

    suspend fun postAgenda(agendaRequest: AgendaRequest):
    Flow<Result<PostAgendaEntity, ResponseObjectWrapper<AgendaResponse>>>{
        return repository.postAgenda(agendaRequest)
    }


    //belom diperbaiki
    suspend fun register(): Flow<Result<SementaraEntity, ResponseObjectWrapper<RegisterResponse>>> {
        return repository.register()
    }
}