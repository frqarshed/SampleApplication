package com.sample.data.core.impl

import com.sample.data.core.dao.ClassifiedListingDao
import com.sample.domain.model.Ads
import com.sample.domain.model.Result
import com.sample.domain.repository.ClassifiedListingRepository
import javax.inject.Inject

class ClassifiedListingRepositoryImpl @Inject constructor(
    private val classifiedListingDao: ClassifiedListingDao
) : ClassifiedListingRepository {

    override suspend fun getAllAds(): Result<Ads> {
        return classifiedListingDao.getAllAds()
    }
}