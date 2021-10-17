package com.google.samples.apps.sunflower.stresstest

import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
@SmallTest
class AddSingle5 : BaseStressTest() {

    @Test
    fun addSingle_1() = addFirstPlant()

    @Test
    fun addSingle_2() = addFirstPlant()

    @Test
    fun addSingle_3() = addFirstPlant()

    @Test
    fun addSingle_4() = addFirstPlant()

    @Test
    fun addSingle_5() = addFirstPlant()
}