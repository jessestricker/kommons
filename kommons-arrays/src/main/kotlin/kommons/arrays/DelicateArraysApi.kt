package kommons.arrays

import kotlin.annotation.AnnotationRetention.BINARY
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.FUNCTION

/**
 * Marks API as _delicate_.
 *
 * Delicate API requires extra care to be used correctly and thus should be used carefully. Please
 * make sure to read and understand the documentation describing a delicate API.
 */
@RequiresOptIn
@MustBeDocumented
@Target(CLASS, FUNCTION)
@Retention(BINARY)
public annotation class DelicateArraysApi
