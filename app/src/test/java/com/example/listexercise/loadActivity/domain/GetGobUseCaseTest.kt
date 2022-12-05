package com.example.listexercise.loadActivity.domain

import com.example.listexercise.loadActivity.data.Repository
import com.example.listexercise.loadActivity.data.model.CacheGob
import com.example.listexercise.loadActivity.data.model.GobRest
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
    fun whenTheResponseBeEmptyThenUseTheCache() = runBlocking {
        //Given
        coEvery { repo.getInfoGob()} returns CacheGob.gobCache
        //When
        var response = getGobUseCase()
        //Then
        coVerify(exactly = 1) { response }
        assert(response==CacheGob.gobCache)
    }
    @Test
    fun verifyResponseNotNullWhenTheApiFail() = runBlocking {
        //Given
        coEvery { repo.getInfoGob()} returns CacheGob.gobCache
        //When
        var response = getGobUseCase()
        //Then
        assert(response!=null)
    }
}