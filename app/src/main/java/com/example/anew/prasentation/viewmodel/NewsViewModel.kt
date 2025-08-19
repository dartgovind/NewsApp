package com.example.anew.prasentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anew.data.model.NewsDto
import com.example.anew.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.anew.util.Result
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
):ViewModel() {
    private val _newsState= MutableStateFlow<Result<NewsDto>>(Result.Idle)
    val newsState:StateFlow<Result<NewsDto>> = _newsState
    fun getNews(){
        viewModelScope.launch {
            _newsState.value=Result.Loading
            try{
                val newsdata=newsRepository.getNews()
                _newsState.value=Result.Success(newsdata)
            }catch (e:Exception){
                _newsState.value=Result.Error(e.message?:"Unknown error")
            }
        }
    }
}