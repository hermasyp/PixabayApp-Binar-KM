package com.catnip.pixabayapp.data.network.datasource

import com.catnip.pixabayapp.data.network.services.PixabayApiService
import com.catnip.pixabayapp.model.SearchResponse

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface SearchDataSource {
    suspend fun searchPhoto(query: String): SearchResponse
}

class PixabayApiDataSourceImpl(private val api: PixabayApiService) :
    SearchDataSource {

    override suspend fun searchPhoto(query: String): SearchResponse {
        return api.searchPhoto(query)
    }

}

class InstagramApiDataSourceImpl(private val api: PixabayApiService) :
    SearchDataSource {

    override suspend fun searchPhoto(query: String): SearchResponse {
        return api.searchPhoto(query)
    }

}
