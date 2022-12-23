package com.example.listexercise.loadActivity.data

import com.example.listexercise.loadActivity.data.database.dao.Queries
import com.example.listexercise.loadActivity.data.database.entities.ListResultQuote
import com.example.listexercise.loadActivity.data.network.model.ListResult
import com.example.listexercise.loadActivity.data.network.GobService
import com.example.listexercise.loadActivity.domain.model.GobModel
import com.example.listexercise.loadActivity.domain.model.toDomain
import javax.inject.Inject

class Repository @Inject constructor(
    private val api : GobService,
    private val dao : Queries
) {
    suspend fun getInfoGob(): List<GobModel> {
        var response: List<ListResult> = api.getGobServices().gobEntity
        return response.map { it.toDomain() }
    }
    suspend fun getInfoGobDao(id:Int): List<GobModel>{
        var response: List<ListResultQuote> = dao.getGobPage(id)
        return response.map { it.toDomain() }
    }
    suspend fun getInfoGobDaoFull(): List<GobModel>{
        var response: List<ListResultQuote> = dao.getAllQuotes()
        return response.map { it.toDomain() }
    }
    suspend fun insertApiToDatabase(gob: List<ListResultQuote>){
        dao.insertAll(gob)
    }

}