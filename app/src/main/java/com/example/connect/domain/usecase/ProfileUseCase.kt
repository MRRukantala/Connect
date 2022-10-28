package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.auth.ResponseObjectWrapperTanpaData
import com.example.connect.data.model.request.*
import com.example.connect.data.model.response.*
import com.example.connect.domain.entity.*
import com.example.connect.domain.repo.ProfileApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileUseCase @Inject constructor(private val repository: ProfileApiRepository) {
    suspend fun getProfile(id:Int):
            Flow<Result<List<ProfileEntity>, ResponseListWrapper<ProfileResponse>>> {
        return repository.getProfile(id)
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

    suspend fun deletePendidikan(id: Int): Flow<Result<DeletePendidikanEntity, ResponseObjectWrapper<DeletePendidikanResponse>>>{
        return repository.deletePendidikan(id)
    }

    suspend fun postPendidikan(pendidikanRequest: PendidikanRequest):
            Flow<Result<PostPendidikanEntity, ResponseObjectWrapperTanpaData>>{
        return repository.postPendidikan(pendidikanRequest)
    }
}