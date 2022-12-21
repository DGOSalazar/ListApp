package com.example.listexercise.loadActivity.domain

import com.example.listexercise.loadActivity.data.Repository
import com.example.listexercise.loadActivity.domain.model.GobModel
import javax.inject.Inject

class GetPageUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id:Int): List<GobModel> = repository.getInfoGobDao(id)
}