package com.example.connect.domain.repo

import com.example.connect.data.auth.ResponseListWrapper
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.data.database.SavedProductDAO
import com.example.connect.utilites.base.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DatabaseRepository  {
    suspend fun getDataKeranjangByIdUser(id: Int): Flow<Result<List<SaveProductDataEntity>, List<SaveProductDataEntity>>>
    suspend fun insertData(data: SaveProductDataEntity): Flow<SavedProductDAO>
}