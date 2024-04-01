package com.example.myapp.ui.apidatalist.repository

import com.example.myapp.interfaces.ApplicationAPI
import com.example.myapp.ui.apidatalist.model.ApiListResultWrapper
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiListRepository @Inject constructor(private val appApi: ApplicationAPI)  {
    suspend fun getAPIList(): Response<ApiListResultWrapper> {
        return appApi.getAPIList()
    }
}

