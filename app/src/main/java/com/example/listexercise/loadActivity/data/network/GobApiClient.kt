package com.example.listexercise.loadActivity.data.network


import com.example.listexercise.loadActivity.data.network.model.GobRest
import retrofit2.Response
import retrofit2.http.GET

interface GobApiClient{
    @GET("gobmx.facts")
    suspend fun getGob():Response<GobRest>
}