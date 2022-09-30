package com.catnip.pixabayapp.provider

import android.content.Context
import com.catnip.pixabayapp.data.network.datasource.PixabayApiDataSourceImpl
import com.catnip.pixabayapp.data.network.datasource.SearchDataSource
import com.catnip.pixabayapp.data.network.services.PixabayApiService
import com.catnip.pixabayapp.data.repository.SearchRepository
import com.catnip.pixabayapp.data.repository.SearchRepositoryImpl
import com.chuckerteam.chucker.api.ChuckerInterceptor

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object ServiceLocator {

    fun provideChucker(appContext: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(appContext).build()
    }

    fun provideApiService(chuckerInterceptor: ChuckerInterceptor): PixabayApiService {
        return PixabayApiService.invoke(chuckerInterceptor)
    }

    fun provideDataSource(apiService: PixabayApiService): SearchDataSource {
        return PixabayApiDataSourceImpl(apiService)
    }

    fun provideRepository(context: Context): SearchRepository {
        return SearchRepositoryImpl(provideDataSource(provideApiService(provideChucker(context))))
    }

}