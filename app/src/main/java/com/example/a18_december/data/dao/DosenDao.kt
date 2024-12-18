package com.example.a18_december.data.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.example.a18_december.data.entity.Dosen


@Dao
interface DosenDao {
    @Query("select * from dosen ORDER BY nama ASC")
}