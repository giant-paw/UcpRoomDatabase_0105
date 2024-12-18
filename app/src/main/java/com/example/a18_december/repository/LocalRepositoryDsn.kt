package com.example.a18_december.repository

import com.example.a18_december.data.dao.DosenDao
import java.util.concurrent.Flow

class LocalRepositoryDsn(
    private val dosenDao: DosenDao
) : RepositoryDsn {
    override fun getAllDosen(): Flow<List>Dosen>> {
        return dosenDao.getAllMahasiswa()
    }

}
