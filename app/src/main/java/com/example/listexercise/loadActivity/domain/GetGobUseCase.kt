package com.example.listexercise.loadActivity.domain

import com.example.listexercise.loadActivity.data.Repository
import com.example.listexercise.loadActivity.data.model.GobRest
import javax.inject.Inject

class GetGobUseCase @Inject constructor(
    private val repository: Repository
){
    suspend operator fun invoke(): GobRest = repository.getInfoGob()
}