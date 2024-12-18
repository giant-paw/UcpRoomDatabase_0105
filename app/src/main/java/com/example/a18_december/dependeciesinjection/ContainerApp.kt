package com.example.a18_december.dependeciesinjection

import android.content.Context
import com.example.a18_december.data.database.UmyDatabase
import com.example.a18_december.repository.LocalRepositoryDsn
import com.example.a18_december.repository.RepositoryDsn

interface InterfaceContainerApp {
    val repositoryDsn : RepositoryDsn
}
class ContainerApp(private val context: Context) : InterfaceContainerApp {
    override val repositoryDsn: RepositoryDsn by lazy {
        LocalRepositoryDsn(UmyDatabase.getDatabase(context).dosenDao())
    }
}