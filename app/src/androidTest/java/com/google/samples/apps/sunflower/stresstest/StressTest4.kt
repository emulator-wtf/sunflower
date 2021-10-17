package com.google.samples.apps.sunflower.stresstest

import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
@MediumTest
class StressTest4 : BaseStressTest() {

    @Test
    fun roundTrip_1() = addAllAndOpen()

    @Test
    fun roundTrip_2() = addAllAndOpen()

    @Test
    fun roundTrip_3() = addAllAndOpen()

    @Test
    fun roundTrip_4() = addAllAndOpen()
}

