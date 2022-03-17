package com.sample.data.core.dao

import com.sample.data.core.network.api.ClassifiedListingApi
import com.sample.data.core.network.toDomain
import com.sample.domain.model.Ads
import com.sample.domain.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ClassifiedListingDao @Inject constructor(
    private val api: ClassifiedListingApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getAllAds(): Result<Ads> =
        withContext(ioDispatcher) {
            try {
                val result = api.getAllAds()
                Result.Success(result.toDomain())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

}
