package com.sample.myapplication.core.di

import com.sample.domain.model.Ads
import com.sample.domain.model.Result
import com.sample.domain.repository.ClassifiedListingRepository
import com.sample.myapplication.core.utils.FakeDataGenerator

class FakeClassifiedListingRepository : ClassifiedListingRepository {
    private val ads = Ads(FakeDataGenerator.getFakeAds())
    override suspend fun getAllAds(): Result<Ads> {
        return Result.Success(ads)
    }
}