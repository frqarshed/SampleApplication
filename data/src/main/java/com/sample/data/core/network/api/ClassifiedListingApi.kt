package com.sample.data.core.network.api

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Headers


interface ClassifiedListingApi {
    @GET("default/dynamodb-writer")
    @Headers("Content-type: application/json")
    suspend fun getAllAds(): GetAllAdsResponse
}


data class GetAllAdsResponse(
    @SerializedName("pagination") val pagination: Pagination,
    @SerializedName("results") val results: List<Result>
)

data class Pagination(
    @SerializedName("key") val key: Any
)

data class Result(
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("image_ids") val imageIds: List<String>,
    @SerializedName("image_urls") val imageUrls: List<String>,
    @SerializedName("image_urls_thumbnails") val imageUrlsThumbnails: List<String>,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("uid") val uid: String
)