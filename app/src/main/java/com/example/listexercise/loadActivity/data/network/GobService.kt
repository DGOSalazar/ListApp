package com.example.listexercise.loadActivity.data.network

import android.util.Log
import com.example.listexercise.loadActivity.data.network.model.GobRest
import com.example.listexercise.loadActivity.data.network.model.ListResult
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

class GobService @Inject constructor(
    private val api:GobApiClient
){
    private lateinit var response :Response<GobRest>
    suspend fun getGobServices(): GobRest {
        return with(Dispatchers.IO){
            response = api.getGob()
            return statusResponse(response)
        }
    }
    private fun statusResponse(r:Response<GobRest>): GobRest{
        if (r.code()!=200) Log.i("error code","${r.code()}")
        return when(r.code()){
            200 -> response.body() ?: GobRest()
            400 -> GobRest(gobEntity = arrayListOf(ListResult(statusError = "your mistake")))
            500 -> GobRest(gobEntity = arrayListOf(ListResult(statusError = "server error")))
            else -> GobRest(gobEntity = arrayListOf(ListResult(statusError = "any")))
        }
    }
}