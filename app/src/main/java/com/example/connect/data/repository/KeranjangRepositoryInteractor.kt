package com.example.connect.data.repository

import android.util.Log
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.data.database.SavedProductDAO
import com.example.connect.domain.repo.DatabaseRepository
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KeranjangRepositoryInteractor @Inject constructor(private val dao: SavedProductDAO) :
    DatabaseRepository {
    override suspend fun getDataKeranjangByIdUser(id: Int): Flow<Result<List<SaveProductDataEntity>, List<SaveProductDataEntity>>> {
        return flow {
            val data = dao.getByIdUser(id)
            emit(Result.Success(data))
        }
    }

    override suspend fun insertData(data: SaveProductDataEntity): Flow<SavedProductDAO> {
        return flow {
           dao.insert(data)
        }
    }


}