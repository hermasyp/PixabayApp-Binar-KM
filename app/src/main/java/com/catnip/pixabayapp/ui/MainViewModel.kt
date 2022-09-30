package com.catnip.pixabayapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.catnip.pixabayapp.data.repository.SearchRepository
import com.catnip.pixabayapp.model.SearchResponse
import com.catnip.pixabayapp.wrapper.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class MainViewModel(private val repository: SearchRepository) : ViewModel() {

    val searchResult = MutableLiveData<Resource<SearchResponse>>()

    fun searchPost(query: String) {
        searchResult.postValue(Resource.Loading())
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.searchPhoto(query)
            viewModelScope.launch(Dispatchers.Main) {
                searchResult.postValue(data)
            }
        }
    }
}