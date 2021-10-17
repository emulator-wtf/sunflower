package com.google.samples.apps.sunflower.stresstest

import androidx.test.filters.LargeTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
@LargeTest
class StressTest6 : BaseStressTest() {

    @Test
    fun roundTrip_1() = addAllAndOpen()

    @Test
    fun roundTrip_2() = addAllAndOpen()

    @Test
    fun roundTrip_3() = addAllAndOpen()

    @Test
    fun roundTrip_4() = addAllAndOpen()

    @Test
    fun roundTrip_5() = addAllAndOpen()

    @Test
    fun roundTrip_6() = addAllAndOpen()
}

