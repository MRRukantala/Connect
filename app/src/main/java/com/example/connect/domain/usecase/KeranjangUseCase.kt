package com.example.connect.domain.usecase

import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.domain.repo.DatabaseRepository
import javax.inject.Inject

class KeranjangUseCase @Inject constructor(private val dao: DatabaseRepository) {
    suspend fun getDataKeranjangByIdUser(id: Int) = dao.getDataKeranjangByIdUser(id)
    suspend fun insertData(data: SaveProductDataEntity) = dao.insertData(data)
    suspend fun deleteData(id: Long) = dao.deleteData(id)
}