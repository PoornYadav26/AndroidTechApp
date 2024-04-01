package com.example.myapp.interfaces


import com.example.myapp.ui.apilist.activity.model.ApiListResultWrapper
import retrofit2.Response
import retrofit2.http.GET


interface ApplicationAPI {
   @GET("/entries")
   suspend fun getAPIList(): Response<ApiListResultWrapper>

}