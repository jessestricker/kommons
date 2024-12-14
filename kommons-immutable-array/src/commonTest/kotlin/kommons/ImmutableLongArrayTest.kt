package kommons

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertSame
import kotlin.test.assertTrue

class ImmutableLongArrayTest {
    @Test
    fun indexOf() {
        val array = longArrayOf(9, 10, 11, 12, 13).toImmutableArray(1..3)

        assertEquals(-1, array.indexOf(8))
        assertEquals(-1, array.indexOf(9))

        assertEquals(0, array.indexOf(10))
        assertEquals(1, array.indexOf(11))
        assertEquals(2, array.indexOf(12))

        assertEquals(-1, array.indexOf(13))
        assertEquals(-1, array.indexOf(14))
    }

    @Test
    fun contains() {
        val array = longArrayOf(9, 10, 11, 12, 13).toImmutableArray(1..3)

        assertFalse { array.contains(8) }
        assertFalse { array.contains(9) }

        assertTrue { array.contains(10) }
        assertTrue { array.contains(11) }
        assertTrue { array.contains(12) }

        assertFalse { array.contains(13) }
        assertFalse { array.contains(14) }
    }

    @Test
    fun `subArray WITH full range`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val subArray = array.subArray(0..4)

        assertSame(array.data, subArray.data)
        assertEquals(0, subArray.dataRange.first)
        assertEquals(4, subArray.dataRange.last)
    }

    @Test
    fun `subArray WITH first one greater`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val subArray = array.subArray(1..4)

        assertSame(array.data, subArray.data)
        assertEquals(1, subArray.dataRange.first)
        assertEquals(4, subArray.dataRange.last)
    }

    @Test
    fun `subArray WITH last one less`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val subArray = array.subArray(0..3)

        assertSame(array.data, subArray.data)
        assertEquals(0, subArray.dataRange.first)
        assertEquals(3, subArray.dataRange.last)
    }

    @Test
    fun `subArray WITH first one greater AND last one less`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val subArray = array.subArray(1..3)

        assertSame(array.data, subArray.data)
        assertEquals(1, subArray.dataRange.first)
        assertEquals(3, subArray.dataRange.last)
    }

    @Test
    fun `subArray WITH singleton range`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val subArray = array.subArray(2..2)

        assertSame(array.data, subArray.data)
        assertEquals(2, subArray.dataRange.first)
        assertEquals(2, subArray.dataRange.last)
    }

    @Test
    fun `subArray WITH empty range`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        @Suppress("InvalidRange") val subArray = array.subArray(3..2)

        assertTrue { subArray.dataRange.isEmpty() }
    }

    @Test
    fun `subArray throws WHEN first too small`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        assertFailsWith<IndexOutOfBoundsException> { array.subArray(-1..4) }
    }

    @Test
    fun `subArray throws WHEN first too large`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        assertFailsWith<IndexOutOfBoundsException> {
            @Suppress("InvalidRange") array.subArray(5..4)
        }
    }

    @Test
    fun `subArray throws WHEN last too small`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        assertFailsWith<IndexOutOfBoundsException> { array.subArray(0..-1) }
    }

    @Test
    fun `subArray throws WHEN last too large`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        assertFailsWith<IndexOutOfBoundsException> { array.subArray(0..5) }
    }
}
