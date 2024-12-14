package kommons.internal

internal val IntRange.size: Int
    get() = if (isEmpty()) 0 else last - first + 1
