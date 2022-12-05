package com.example.listexercise.loadActivity.di

import com.example.listexercise.loadActivity.data.network.GobApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return retrofit2.Retrofit.Builder()
            .baseUrl("https://api.datos.gob.mx/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    @Singleton
    @Provides
    fun provideGobApiClient(retrofit: Retrofit):GobApiClient{
        return retrofit.create(GobApiClient::class.java)
    }

}