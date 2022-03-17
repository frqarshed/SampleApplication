package com.sample.myapplication.presentation.ui.main.details

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.sample.myapplication.R
import com.sample.myapplication.launchFragmentInHiltContainer
import com.sample.myapplication.ui.main.details.AdDetailsJavaFragment
import com.sample.myapplication.utils.FakeDataGenerator
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


@HiltAndroidTest
class AdDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun check_title_and_price_in_detail_view() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<AdDetailsJavaFragment>(Bundle().apply {
            putSerializable("ad", FakeDataGenerator.getFakeAds()[0])
        }) {
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.title)).check(matches(withText("Fake Ad 1")))
        onView(withId(R.id.price)).check(matches(withText("AED 100")))
    }
}