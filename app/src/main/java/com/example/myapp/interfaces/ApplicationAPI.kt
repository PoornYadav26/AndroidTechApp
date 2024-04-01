package com.example.myapp.interfaces


import com.example.myapp.ui.apidatalist.model.ApiListResultWrapper
import retrofit2.Response
import retrofit2.http.GET


interface ApplicationAPI {
   @GET("/entries")
   suspend fun getAPIList(): Response<ApiListResultWrapper>

}