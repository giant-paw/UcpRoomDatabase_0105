package com.example.a18_december.repository

import java.util.concurrent.Flow
import com.example.a18_december.data.entity.Dosen

interface RepositoryDsn {
    fun getAllDosen():Flow<List>Dosen>>
}