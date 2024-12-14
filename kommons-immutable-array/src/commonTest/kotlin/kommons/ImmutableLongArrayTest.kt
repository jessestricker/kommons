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
        val array = longArrayOf(9, 10, 11, 12, 13).toImmutableArray(1, 4)

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
        val array = longArrayOf(9, 10, 11, 12, 13).toImmutableArray(1, 4)

        assertFalse { array.contains(8) }
        assertFalse { array.contains(9) }

        assertTrue { array.contains(10) }
        assertTrue { array.contains(11) }
        assertTrue { array.contains(12) }

        assertFalse { array.contains(13) }
        assertFalse { array.contains(14) }
    }

    @Test
    fun `sliceArray WITH full range`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val sliceArray = array.sliceArray(0, 5)

        assertSame(array.data, sliceArray.data)
        assertEquals(0, sliceArray.dataStart)
        assertEquals(5, sliceArray.dataEnd)
    }

    @Test
    fun `sliceArray WITH first one greater`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val sliceArray = array.sliceArray(1, 5)

        assertSame(array.data, sliceArray.data)
        assertEquals(1, sliceArray.dataStart)
        assertEquals(5, sliceArray.dataEnd)
    }

    @Test
    fun `sliceArray WITH last one less`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val sliceArray = array.sliceArray(0, 4)

        assertSame(array.data, sliceArray.data)
        assertEquals(0, sliceArray.dataStart)
        assertEquals(4, sliceArray.dataEnd)
    }

    @Test
    fun `sliceArray WITH first one greater AND last one less`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val sliceArray = array.sliceArray(1, 4)

        assertSame(array.data, sliceArray.data)
        assertEquals(1, sliceArray.dataStart)
        assertEquals(4, sliceArray.dataEnd)
    }

    @Test
    fun `sliceArray WITH singleton range`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val sliceArray = array.sliceArray(2, 3)

        assertSame(array.data, sliceArray.data)
        assertEquals(2, sliceArray.dataStart)
        assertEquals(3, sliceArray.dataEnd)
    }

    @Test
    fun `sliceArray WITH empty range`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        val sliceArray = array.sliceArray(3, 3)

        assertTrue { sliceArray.dataStart >= sliceArray.dataEnd }
    }

    @Test
    fun `sliceArray throws WHEN first too small`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        assertFailsWith<IllegalArgumentException> { array.sliceArray(-1, 5) }
    }

    @Test
    fun `sliceArray throws WHEN last too large`() {
        val array = immutableLongArrayOf(10, 11, 12, 13, 14)

        assertFailsWith<IllegalArgumentException> { array.sliceArray(0, 6) }
    }
}
