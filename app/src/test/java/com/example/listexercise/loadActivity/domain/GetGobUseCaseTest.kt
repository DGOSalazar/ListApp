package com.example.listexercise.loadActivity.domain

import com.example.listexercise.loadActivity.data.Repository
import com.example.listexercise.loadActivity.domain.model.GobModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class GetGobUseCaseTest {
    @RelaxedMockK
    private lateinit var repo : Repository
    private lateinit var getGobUseCase:GetGobUseCase
    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getGobUseCase=GetGobUseCase(repo)
    }
    @Test
    fun whenTheApiNotReturnAnythingGetFromDatabase() = runBlocking {
        //Given
        coEvery { repo.getInfoGob()} returns emptyList()
        //When
        getGobUseCase()
        //Then
        coVerify(exactly = 1) { repo.getInfoGobDaoFull() }
    }
}