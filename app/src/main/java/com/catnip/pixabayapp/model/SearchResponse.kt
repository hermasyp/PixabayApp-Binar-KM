package com.catnip.pixabayapp.model

import com.google.gson.annotations.SerializedName

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
data class SearchResponse(
    @SerializedName("total")
    val total : Int?,
    @SerializedName("totalHits")
    val totalHits : Int?,
    @SerializedName("hits")
    val posts : List<Post>?
)
