package com.google.samples.apps.sunflower.stresstest

import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
@MediumTest
class StressTest1 : BaseStressTest() {

    @Test
    fun roundTrip_1() = addAllAndOpen()
}

