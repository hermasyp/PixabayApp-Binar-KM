package com.catnip.pixabayapp.data.repository

import com.catnip.pixabayapp.data.network.datasource.SearchDataSource
import com.catnip.pixabayapp.model.SearchResponse
import com.catnip.pixabayapp.wrapper.Resource

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SearchRepository {
    suspend fun searchPhoto(q: String): Resource<SearchResponse>
}

class SearchRepositoryImpl(private val dataSource: SearchDataSource) : SearchRepository {

    override suspend fun searchPhoto(q: String): Resource<SearchResponse> {
        return try {
            val data = dataSource.searchPhoto(q)
            if (data.posts.isNullOrEmpty()) Resource.Empty() else Resource.Success(data)
        } catch (exception: Exception) {
            Resource.Error(exception)
        }
    }

}