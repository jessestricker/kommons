package kommons.internal

internal fun requireIndex(index: Int, size: Int) {
    if (index < 0 || index >= size) {
        val message = "index $index out of bounds 0..<$size"
        throw IndexOutOfBoundsException(message)
    }
}
