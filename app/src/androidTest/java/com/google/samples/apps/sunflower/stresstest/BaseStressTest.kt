package com.google.samples.apps.sunflower.stresstest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.samples.apps.sunflower.GardenActivity
import com.google.samples.apps.sunflower.PlantDetailFragment
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Rule
import org.junit.rules.RuleChain

open class BaseStressTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(GardenActivity::class.java)

    private val plantList = getPlantList().sortedBy { it.name }

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    fun addFirstPlant() {
        onView(withText(R.string.plant_list_title))
            .perform(click())
        addSingle(0, plantList[0])
    }

    private fun addSingle(index: Int = 0, plant: Plant = plantList[0]) {
        // Open the plants detail view
        onView(withId(R.id.plant_list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    index,
                    click()
                )
            )

        // Verify tha the plant name is displayed
        onView(withId(R.id.plant_detail_name))
            .check(matches(withText(plant.name)))

        // Click on the "Add" button
        onView(withId(R.id.fab))
            .check(matches(isDisplayed()))
        onView(withId(R.id.fab)).perform(click())

        // Go back to the "Plant list" tab
        pressBack()
    }

    fun addAll() {
        // Click the "Plant list" tab
        onView(withText(R.string.plant_list_title)).perform(click())
        plantList.forEachIndexed { index, plant ->
            addSingle(index, plant)
        }
    }

    fun addAllAndOpen() {
        plantList.forEachIndexed { index, plant ->
            // Click the "Plant list" tab
            onView(withText(R.string.plant_list_title)).perform(click())

            // Add the plant
            addSingle(index, plant)

            // Go to the "My garden" tab
            onView(withText(R.string.my_garden_title)).perform(click())
            // Open the detail view of a plant (not necessarily the same plant that was just added as
            // the order here is different)
            onView(withId(R.id.garden_list))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        index,
                        click()
                    )
                )
            // Go back to the "My garden" tab
            pressBack()
        }
    }

    private fun getPlantList(): List<Plant> {
        return InstrumentationRegistry.getInstrumentation().targetContext.assets.open("plants.json")
            .use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    Gson().fromJson(jsonReader, plantType)
                }
            }
    }
}