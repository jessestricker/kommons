package de.jessestricker.kommons.array

/**
 * Marks declarations that should be used carefully because they may break type safety or
 * invariants.
 *
 * Consider using other APIs instead when possible. Otherwise, make sure to read documentation
 * describing a delicate API.
 */
@MustBeDocumented
@RequiresOptIn(
    message =
        "This is a delicate API and its use requires care. " +
            "Make sure you fully read and understand documentation " +
            "of the declaration that is marked as a delicate API.",
    level = RequiresOptIn.Level.WARNING,
)
public annotation class DelicateKommonsArrayApi
