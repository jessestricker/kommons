package kommons.internal

internal inline fun requireIndex(value: Boolean, buildMessage: () -> String) {
    if (!value) {
        val message = buildMessage()
        throw IndexOutOfBoundsException(message)
    }
}
