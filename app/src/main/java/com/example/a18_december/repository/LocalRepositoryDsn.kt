package com.example.a18_december.repository

import com.example.a18_december.data.dao.DosenDao
import com.example.a18_december.data.entity.Dosen
import java.util.concurrent.Flow

class LocalRepositoryDsn(
    private val dosenDao: DosenDao
) : RepositoryDsn {

    override suspend fun insertDsn(dosen: Dosen) {
        dosenDao.insertDsn(dosen)
    }

    override fun getAllDosen(): kotlinx.coroutines.flow.Flow<List<Dosen>> {
        return dosenDao.getAllDosen()
    }

    override fun getDsn(nidn: String): kotlinx.coroutines.flow.Flow<Dosen> {
        return dosenDao.getDosen(nidn)
    }

}
