package com.example.listexercise.loadActivity.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listexercise.loadActivity.domain.GetGobUseCase
import com.example.listexercise.loadActivity.domain.GetPageUseCase
import com.example.listexercise.loadActivity.domain.model.GobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGobUseCase:GetGobUseCase,
    private val getPageUseCase: GetPageUseCase
):ViewModel(){
    val gobModel = MutableLiveData<List<GobModel>>()
    val gobModelPagination = MutableLiveData<List<GobModel>>()
    val gobData = MutableLiveData<GobModel>()
    val isLoading = MutableLiveData<Boolean>()
    private var count = MutableLiveData<Int>()
    private var page=0

    fun onGetData(){
        viewModelScope.launch {
            val result = getGobUseCase()
            if (result.isNotEmpty()) {
                gobModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
    fun onPagination(){
        viewModelScope.launch {
            val result = getPageUseCase(page)
            gobModelPagination.postValue(result)
            isLoading.postValue(false)
        }
    }
    fun onItemSelect(result: GobModel){
        gobData.postValue(result)
    }
    fun onNextPage(){
        if (page<90) page+=10;count.postValue(page)
    }
    fun onPreviousPage(){
        if (page>0) page-=10;count.postValue(page)
    }
    fun onLoading(){
        isLoading.postValue(true)
    }
}