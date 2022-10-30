package com.example.connect.domain.usecase

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.auth.ResponseListWrapperSementara
import com.example.connect.data.auth.ResponseObjectWrapper
import com.example.connect.data.model.response.AgendaResponse
import com.example.connect.data.model.response.KirimanResponse
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.domain.entity.KirimanEntity
import com.example.connect.domain.repo.HomeApiRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeUseCase @Inject constructor(private val repository: HomeApiRepository) {

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
            Flow<Result<ArrayList<KirimanEntity>, ResponseListWrapperSementara<KirimanResponse>>> {
        return repository.getAllKiriman()
    }

    suspend fun getKirimanByIdUser(id: Int):
            Flow<Result<ArrayList<KirimanEntity>, ResponseListWrapper<KirimanResponse>>> {
        return repository.getKirimanByIdUser(id)
    }

    suspend fun getDetailKiriman(id: Int):
            Flow<Result<List<KirimanEntity>, ResponseListWrapper<KirimanResponse>>> {
        return repository.getDetailKiriman(id)
    }
}