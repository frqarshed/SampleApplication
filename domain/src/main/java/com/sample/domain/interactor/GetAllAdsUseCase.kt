package com.sample.domain.interactor

import com.sample.domain.interactor.base.UseCase
import com.sample.domain.model.Ads
import com.sample.domain.model.OrderType
import com.sample.domain.model.Result
import com.sample.domain.repository.ClassifiedListingRepository
import javax.inject.Inject

class GetAllAdsUseCase @Inject constructor(
    private val repo: ClassifiedListingRepository
) : UseCase<Result<Ads>, GetAllAdsUseCase.Params>() {

    override suspend fun execute(params: Params?): Result<Ads> {
        params!!
        return when (val result = repo.getAllAds()) {
            is Result.Error -> {
                result
            }
            is Result.Success<Ads> -> {
                val sortedList = when (params.orderType) {
                    OrderType.Ascending -> result.data.ads.sortedBy { it.name }
                    OrderType.Descending -> result.data.ads.sortedByDescending { it.name }
                }
                Result.Success(Ads(sortedList))
            }
        }
    }

    data class Params(val orderType: OrderType)
}