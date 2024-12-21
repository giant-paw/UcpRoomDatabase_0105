package com.example.a18_december.repository

import com.example.a18_december.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

interface RepositoryDsn {

    suspend fun insertDsn(dosen: Dosen)

    fun getAllDosen(): Flow<List<Dosen>>

    fun getDsn(nim: String): Flow<Dosen>
}