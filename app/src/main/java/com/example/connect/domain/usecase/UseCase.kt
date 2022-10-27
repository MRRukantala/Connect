package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperSementara
import com.example.connect.data.model.request.*
import com.example.connect.data.model.response.*
import com.example.connect.data.model.response.elearning.PlaylistElearningResponse
import com.example.connect.data.model.response.elearning.VideoELearningResponse
import com.example.connect.domain.entity.*
import com.example.connect.domain.entity.elearning.PlaylistELearningEntity
import com.example.connect.domain.entity.elearning.VideoELearningEntity
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
    Flow<Result<LayananEntity, ResponseObjectWrapper<DetailArtikelResponse>>>{
        return repository.getDetailLayanan(id)
    }

    suspend fun getProfile(id:Int):
    Flow<Result<List<ProfileEntity>, ResponseListWrapper<ProfileResponse>>>{
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
    Flow<Result<List<PostAgendaEntity>, ResponseListWrapper<AgendaResponse>>>{
        return repository.postAgenda(agendaRequest)
    }

    suspend fun posKiriman(kirimanRequest: KirimanRequest):
    Flow<Result<PostKirimanEntity, ResponseObjectWrapper<PostKirimanResponse>>>{
        return repository.postKiriman(kirimanRequest)
    }

    suspend fun posProduct(productRequest: ProductRequest):
    Flow<Result<List<PostProductEntity>, ResponseListWrapper<PostProductResponse>>>{
        return repository.postProduct(productRequest)
    }

    suspend fun editProfile(profileRequest: ProfileRequest, id:Int, method:Map<String, String>):
    Flow<Result<EditProfleEntity, ResponseObjectWrapper<EditProfileResponse>>>{
        return repository.updateMyProfile(profileRequest, id, method)
    }

    suspend fun register(registerRequest: RegisterRequest): Flow<Result<RegisterEntity, ResponseObjectWrapper<RegisterResponse>>> {
        return repository.register(registerRequest)
    }

    suspend fun detailProduct(id:Int): Flow<Result<List<DetailProductEntity>, ResponseListWrapperSementara<ProductResponse>>>{
        return repository.getDetailProduct(id)
    }

    suspend fun getAllPlaylist(): Flow<Result<List<PlaylistELearningEntity>, ResponseListWrapper<PlaylistElearningResponse>>> {
        return repository.getAllPlaylist()
    }

    suspend fun getAllVideoELearning(id: Int): Flow<Result<List<VideoELearningEntity>, ResponseListWrapper<VideoELearningResponse>>> {
        return repository.getAllVideoELearning(id)
    }

    suspend fun deletePendidikan(id: Int): Flow<Result<DeletePendidikanEntity, ResponseObjectWrapper<DeletePendidikanResponse>>>{
        return repository.deletePendidikan(id)
    }


    //belom diperbaiki

}