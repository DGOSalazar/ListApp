package com.example.listexercise.loadActivity.di

import android.content.Context
import androidx.room.Room
import com.example.listexercise.loadActivity.data.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val DATABASE_NAME="database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context,Database::class.java, DATABASE_NAME).build()
    @Singleton
    @Provides
    fun provideQueries(database: Database) = database.getDao()
}

