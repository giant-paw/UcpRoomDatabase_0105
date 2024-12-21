package com.example.a18_december.repository

import com.example.a18_december.data.dao.DosenDao
import com.example.a18_december.data.entity.Dosen
import kotlinx.coroutines.flow.Flow

class LocalRepositoryDsn(
    private val dosenDao: DosenDao
) : RepositoryDsn {

    override suspend fun insertDsn(dosen: Dosen) {
        dosenDao.insertDsn(dosen)
    }

    override fun getAllDosen(): Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDsn(nidn: String): Flow<Dosen> {
        return dosenDao.getDosen(nidn)
    }

}
