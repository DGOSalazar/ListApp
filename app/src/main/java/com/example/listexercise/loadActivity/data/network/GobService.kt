package com.example.listexercise.loadActivity.data.network

import com.example.listexercise.loadActivity.data.model.GobRest
import com.example.listexercise.loadActivity.data.model.ListResult
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GobService @Inject constructor(
    private val api:GobApiClient
) {
    suspend fun getGobServices(): GobRest {
        return with(Dispatchers.IO){
            val response = api.getGob("gobmx.facts")
            return response.body() ?: GobRest()
        }
    }
}