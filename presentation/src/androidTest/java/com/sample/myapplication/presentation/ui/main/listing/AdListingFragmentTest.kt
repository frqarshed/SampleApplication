package com.sample.myapplication.presentation.ui.main.listing

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.sample.myapplication.R
import com.sample.myapplication.databinding.ListingFragmentBinding
import com.sample.myapplication.launchFragmentInHiltContainer
import com.sample.myapplication.ui.main.listing.AdListingFragment
import com.sample.myapplication.ui.main.listing.AdListingFragmentDirections
import com.sample.myapplication.ui.main.listing.AdsAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class AdListingFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `scroll_the_ad_list`() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<AdListingFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        Thread.sleep(10000L)

        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    10
                )
            )

        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                    0
                )
            )
    }


    @Test
    fun `click_on_first_item`(): Unit = runBlocking {
        val navController = Mockito.mock(NavController::class.java)

        lateinit var viewBindingAd: ListingFragmentBinding

        launchFragmentInHiltContainer<AdListingFragment> {
            Navigation.setViewNavController(requireView(), navController)
            viewBindingAd = viewBinding
        }

        delay(10000L)

        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ClickOnButtonView()
                )
            )

        val ad = (viewBindingAd.list.adapter as AdsAdapter).getAdAtPosition(0)

        verify(navController).navigate(
            AdListingFragmentDirections.actionAdListingFragmentToAdDetailsJavaFragment(ad),
        )
    }

    @Test
    fun `click_on_last_item`(): Unit = runBlocking {
        val navController = Mockito.mock(NavController::class.java)

        lateinit var viewBindingAd: ListingFragmentBinding

        launchFragmentInHiltContainer<AdListingFragment> {
            Navigation.setViewNavController(requireView(), navController)
            viewBindingAd = viewBinding
        }

        delay(10000L)
        val adapter = (viewBindingAd.list.adapter as AdsAdapter)
        val lastItemIndex = adapter.itemCount - 1

        Espresso.onView(withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    lastItemIndex,
                    ClickOnButtonView()
                )
            )

        val ad = adapter.getAdAtPosition(lastItemIndex)

        verify(navController).navigate(
            AdListingFragmentDirections.actionAdListingFragmentToAdDetailsJavaFragment(ad),
        )
    }

    inner class ClickOnButtonView : ViewAction {
        private var click = ViewActions.click()

        override fun getConstraints(): Matcher<View> {
            return click.constraints
        }

        override fun getDescription(): String {
            return " click on custom button view"
        }

        override fun perform(uiController: UiController, view: View) {
            //btnClickMe -> Custom row item view button
            click.perform(uiController, view.findViewById(R.id.cardView))
        }
    }
}