package com.sample.myapplication.interactor

import com.google.common.truth.Truth.assertThat
import com.sample.domain.interactor.GetAllAdsUseCase
import com.sample.domain.model.Ads
import com.sample.domain.model.OrderType
import com.sample.domain.model.Result
import com.sample.myapplication.core.di.FakeClassifiedListingRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetAllAdsUseCaseTest {

    private lateinit var getAllAdsUseCase: GetAllAdsUseCase
    private lateinit var fakeRepository: FakeClassifiedListingRepository

    @Before
    fun setUp() {
        fakeRepository = FakeClassifiedListingRepository()
        getAllAdsUseCase = GetAllAdsUseCase(fakeRepository)
    }

    @Test
    fun `Get_Ads_Count`() = runBlocking {

        val result = getAllAdsUseCase.execute(GetAllAdsUseCase.Params(OrderType.Ascending))

        assertThat(result).isInstanceOf(Result.Success::class.java)

        val adsResult = result as Result.Success<Ads>

        assertThat(adsResult.data).isNotNull()

        val ads = adsResult.data.ads

        assertThat(ads).isNotNull()

        assertThat(ads).hasSize(5)
    }

    @Test
    fun `Get_Ads_Title`() = runBlocking {

        val result = getAllAdsUseCase.execute(GetAllAdsUseCase.Params(OrderType.Ascending))

        assertThat(result).isInstanceOf(Result.Success::class.java)

        val adsResult = result as Result.Success<Ads>

        assertThat(adsResult.data).isNotNull()

        val ads = adsResult.data.ads

        assertThat(ads).isNotNull()

        for (i in ads.indices) {
            assertThat(ads[i].name).isEqualTo("Fake Ad ${i + 1}")
        }
    }

    @Test
    fun `Get_Ads_Sort_By_Name_Ascending`() = runBlocking {

        val result = getAllAdsUseCase.execute(GetAllAdsUseCase.Params(OrderType.Ascending))

        assertThat(result).isInstanceOf(Result.Success::class.java)

        val adsResult = result as Result.Success<Ads>

        assertThat(adsResult.data).isNotNull()

        val ads = adsResult.data.ads

        assertThat(ads).isNotNull()

        for(i in 0..ads.size - 2) {
            assertThat(ads[i].name).isLessThan(ads[i+1].name)
        }
    }

    @Test
    fun `Get_Ads_Sort_By_Name_Descending`() = runBlocking {

        val result = getAllAdsUseCase.execute(GetAllAdsUseCase.Params(OrderType.Descending))

        assertThat(result).isInstanceOf(Result.Success::class.java)

        val adsResult = result as Result.Success<Ads>

        assertThat(adsResult.data).isNotNull()

        val ads = adsResult.data.ads

        assertThat(ads).isNotNull()

        for(i in 0..ads.size - 2) {
            assertThat(ads[i].name).isGreaterThan(ads[i+1].name)
        }
    }
}