package kommons.internal

internal inline fun requireIndex(value: Boolean, buildMessage: () -> String) {
    if (!value) {
        val message = buildMessage()
        throw IndexOutOfBoundsException(message)
    }
}

internal fun requireIndex(index: Int, size: Int) {
    requireIndex(index in 0..<size) { "index $index must be within range 0..<$size" }
}
