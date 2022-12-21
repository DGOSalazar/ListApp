package com.example.listexercise.loadActivity.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listexercise.loadActivity.data.database.entities.ListResultQuote

@Dao
interface Queries {
    //Se que esto es horrible xD
    @Query("SELECT * FROM gob_table WHERE identity= '5601'+:id or identity='5602'+:id or identity='5603'+:id or identity= '5604'+:id or identity= '5605'+:id or identity= '5606'+:id or identity='5607'+:id or identity= '5608'+:id or identity= '5609'+:id or identity='5610' ORDER BY identity ASC LIMIT 10")
    suspend fun getAllQuotes(id:Int):List<ListResultQuote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<ListResultQuote>)

    @Query("DELETE FROM gob_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM gob_table WHERE slug=:org")
    suspend fun findByOrg(org: String): List<ListResultQuote>
}