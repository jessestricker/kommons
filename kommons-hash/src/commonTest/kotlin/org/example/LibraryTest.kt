package org.example

import kotlin.test.Test
import kotlin.test.assertTrue

class LibraryTest {
    @Test
    fun someLibraryPropertyReturnsTrue() {
        val classUnderTest = Library()
        assertTrue(classUnderTest.someLibraryProperty, "someLibraryProperty should return 'true'")
    }
}
