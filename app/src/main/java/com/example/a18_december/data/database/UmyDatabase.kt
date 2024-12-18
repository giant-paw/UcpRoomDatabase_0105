package com.example.a18_december.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a18_december.data.dao.DosenDao
import com.example.a18_december.data.entity.Dosen

@Database(entities = [Dosen::class], version = 1, exportSchema = false)
abstract class UmyDatabase : RoomDatabase() {

    abstract fun dosenDao(): DosenDao

    companion object {
        @Volatile
        private var Instance: UmyDatabase? = null

        fun getDatabase(context: Context): UmyDatabase {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    UmyDatabase::class.java,
                    "UmyDatabase"
                )

                    .build().also { Instance = it }
            })
        }
    }
}
