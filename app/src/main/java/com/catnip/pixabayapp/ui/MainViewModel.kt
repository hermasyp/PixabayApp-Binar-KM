package com.catnip.pixabayapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.pixabayapp.model.SearchResponse
import com.catnip.pixabayapp.services.PixabayApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel : ViewModel() {

    private val apiService : PixabayApiService by lazy {
        PixabayApiService.invoke()
    }

    val searchResult = MutableLiveData<SearchResponse>()
    val loadingState = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Exception>()

    fun searchPost(query: String) {
        loadingState.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = apiService.searchPhoto(query = query)
                viewModelScope.launch(Dispatchers.Main) {
                    searchResult.postValue(data)
                    loadingState.postValue(false)
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    loadingState.postValue(false)
                    errorState.postValue(e)
                }
            }
        }
    }
}