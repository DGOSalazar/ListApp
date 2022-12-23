package com.example.listexercise.loadActivity.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listexercise.loadActivity.data.database.entities.ListResultQuote

@Dao
interface Queries {
    //Se que esto es horrible xD
    @Query("SELECT * FROM gob_table WHERE identity BETWEEN 1+:id and 10+:id ORDER BY identity ASC LIMIT 10")
    suspend fun getGobPage(id:Int):List<ListResultQuote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ListResultQuote>)

    @Query("SELECT * FROM gob_table")
    suspend fun getAllQuotes():List<ListResultQuote>
}