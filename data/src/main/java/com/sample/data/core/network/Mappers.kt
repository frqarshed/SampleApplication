package com.sample.data.core.network

import com.sample.data.core.extension.toDate
import com.sample.data.core.network.api.GetAllAdsResponse
import com.sample.data.core.network.api.Result
import com.sample.domain.model.Ad
import com.sample.domain.model.Ads

fun GetAllAdsResponse.toDomain(): Ads = Ads(
    ads = results.toDomain()
)

fun List<Result>.toDomain(): List<Ad> {
    val result = mutableListOf<Ad>()
    this.forEach {
        result.add(it.toDomain())
    }
    return result
}

fun Result.toDomain(): Ad = Ad(
    uid = uid,
    name = name,
    price = price,
    createdAt = createdAt.toDate(),
    imageUrls = imageUrls,
    imageUrlsThumbnails = imageUrlsThumbnails
)