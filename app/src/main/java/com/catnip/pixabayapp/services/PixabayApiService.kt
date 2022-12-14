package com.catnip.pixabayapp.services

import com.catnip.pixabayapp.BuildConfig
import com.catnip.pixabayapp.model.SearchResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface PixabayApiService {

    @GET("api")
    suspend fun searchPhoto(
        @Query("q", encoded = true) query: String,
        @Query("image_type") imageType: String = TYPE_PHOTO,
    ): SearchResponse

    companion object {
        private const val TYPE_PHOTO = "photo"

        @JvmStatic
        operator fun invoke(): PixabayApiService {
            val authInterceptor = Interceptor {
                val originRequest = it.request()
                val oldUrl = originRequest.url
                val newUrl = oldUrl.newBuilder().apply {
                    addQueryParameter("key", BuildConfig.API_KEY)
                }.build()
                it.proceed(originRequest.newBuilder().url(newUrl).build())
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(authInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(PixabayApiService::class.java)
        }
    }
}