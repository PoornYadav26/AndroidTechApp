package com.example.myapp.ui.apilist.activity.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.ui.apilist.activity.model.ApiListResultWrapper
import com.example.myapp.ui.apilist.activity.repository.ApiListRepository
import com.example.myapp.util.NetworkUtil.Companion.hasInternetConnection
import com.example.myapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ApiListViewModel @Inject constructor(
    private val apiRepository: ApiListRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val apiList: MutableLiveData<Resource<ApiListResultWrapper>> = MutableLiveData()
    var apiPage = 1
    var apiListResponse: ApiListResultWrapper? = null

    init {
        viewModelScope.launch {
           apiListCall()
        }
    }

    private suspend fun apiListCall(){
        apiList.postValue(Resource.Loading())
        try{
            if(hasInternetConnection(context)){
                Log.d("ListSize","1")
                val response = apiRepository.getAPIList()
                apiList.postValue(handleApiListResponse(response))
            } else{
                apiList.postValue(Resource.Error("No Internet Connection"))
            }
        }
        catch (ex : Exception){
            when(ex){
                is IOException -> apiList.postValue(Resource.Error("Network Failure"))
                else -> apiList.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleApiListResponse(response: Response<ApiListResultWrapper>):
            Resource<ApiListResultWrapper> {
        if (response.isSuccessful) {
            Log.d("ListSize","2")
            response.body()?.let { resultResponse ->
                apiPage++
                if (apiListResponse == null)
                    apiListResponse = resultResponse
                else {
                    val oldStud = apiListResponse?.entries
                    val newStud = resultResponse.entries
                    oldStud!!.addAll(newStud)
                }
                return Resource.Success(apiListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}