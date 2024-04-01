package com.example.myapp.ui.apilist.activity.repository

import com.example.myapp.interfaces.ApplicationAPI
import com.example.myapp.ui.apilist.activity.model.ApiListResultWrapper
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiListRepository @Inject constructor(private val appApi: ApplicationAPI)  {
    suspend fun getAPIList(): Response<ApiListResultWrapper> {
        return appApi.getAPIList()
    }
}

