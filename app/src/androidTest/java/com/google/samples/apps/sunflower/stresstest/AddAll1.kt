package com.google.samples.apps.sunflower.stresstest

import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
@SmallTest
class AddAll1 : BaseStressTest() {

    @Test
    fun addAll_1() = addAll()
}