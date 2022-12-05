package com.example.listexercise.loadActivity.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listexercise.loadActivity.data.model.CacheGob
import com.example.listexercise.loadActivity.data.model.ListResult
import com.example.listexercise.loadActivity.domain.GetGobUseCase
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGobUseCase:GetGobUseCase
):ViewModel() {

    val gobModel = MutableLiveData<List<ListResult>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getGobUseCase()
            if (!result.gobEntity.isNullOrEmpty()) {
                gobModel.postValue(result.gobEntity)
                isLoading.postValue(false)
            }
        }
    }
}