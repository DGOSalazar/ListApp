package com.example.listexercise.loadActivity.domain

import com.example.listexercise.loadActivity.data.Repository
import com.example.listexercise.loadActivity.data.database.entities.toDomain
import com.example.listexercise.loadActivity.domain.model.GobModel
import javax.inject.Inject

class GetGobUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(): List<GobModel>{
        val gobModel = repository.getInfoGob()
        val gobModelLocal = repository.getInfoGobDaoFull()

        return if (gobModel.isNotEmpty() and gobModelLocal.isEmpty()){
            repository.insertApiToDatabase(gobModel.map { it.toDomain() })
            gobModel
        }else gobModelLocal
    }


}