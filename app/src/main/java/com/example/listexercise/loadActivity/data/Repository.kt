package com.example.listexercise.loadActivity.data

import com.example.listexercise.loadActivity.data.model.CacheGob
import com.example.listexercise.loadActivity.data.model.GobRest
import com.example.listexercise.loadActivity.data.network.GobService
import javax.inject.Inject

class Repository @Inject constructor(
    private val api : GobService
) {
    suspend fun getInfoGob(): GobRest {
        var response = api.getGobServices()
        CacheGob.gobCache = response
        return response
    }
}