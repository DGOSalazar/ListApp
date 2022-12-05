package com.example.listexercise.loadActivity.data.network

import com.example.listexercise.loadActivity.data.model.GobRest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface GobApiClient{
    @GET
    suspend fun getGob(@Url url: String):Response<GobRest>
}