private const val NEWLINE = "\n"

fun String.ensureEndsWithNewline(): String {
    return if (!endsWith(NEWLINE)) {
        this + NEWLINE
    } else {
        this
    }
}
