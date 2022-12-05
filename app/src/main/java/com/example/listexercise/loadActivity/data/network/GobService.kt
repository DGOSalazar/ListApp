package com.example.listexercise.loadActivity.data.network

import com.example.listexercise.loadActivity.data.model.CacheGob
import com.example.listexercise.loadActivity.data.model.GobRest
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class GobService @Inject constructor(
    private val api:GobApiClient,
    private val cacheGob: CacheGob
) {
    suspend fun getGobServices(): GobRest {
        return with(Dispatchers.IO){
            val response = api.getGob("gobmx.facts")
            return response.body() ?: CacheGob.gobCache
        }
    }
}