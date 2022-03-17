package com.sample.domain.repository

import com.sample.domain.model.Ads
import com.sample.domain.model.Result

interface ClassifiedListingRepository {
    suspend fun getAllAds(): Result<Ads>
}