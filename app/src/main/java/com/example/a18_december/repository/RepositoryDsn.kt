package com.example.a18_december.repository

import com.example.a18_december.data.entity.Dosen
import java.util.concurrent.Flow

interface RepositoryDsn {

    suspend fun insertDsn(dosen: Dosen)

    fun getAllDosen(): kotlinx.coroutines.flow.Flow<List<Dosen>>

    fun getDsn(nim: String): kotlinx.coroutines.flow.Flow<Dosen>
}