package com.example.listexercise.loadActivity.ui.viewModel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listexercise.loadActivity.domain.GetByOrganizationUseCase
import com.example.listexercise.loadActivity.domain.GetGobUseCase
import com.example.listexercise.loadActivity.domain.GetPageUseCase
import com.example.listexercise.loadActivity.domain.model.GobModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGobUseCase:GetGobUseCase,
    private val getPageUseCase: GetPageUseCase,
    private val getByOrganizationUseCase: GetByOrganizationUseCase
):ViewModel(){
    val gobModel = MutableLiveData<List<GobModel>>()
    val gobModelPagination = MutableLiveData<List<GobModel>>()
    val model = MutableLiveData<GobModel>()
    val mainView = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        viewModelScope.launch {
            val result = getGobUseCase()
            if (result.isNotEmpty()) {
                gobModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }
    fun onPagination(id:Int){
        viewModelScope.launch {
            val result = getPageUseCase(id)
            gobModelPagination.postValue(result)
            isLoading.postValue(false)
        }
    }
    fun onItemSelect(result: GobModel){
        model.postValue(result)
    }
    fun onLoading(){
        isLoading.postValue(true)
    }
}