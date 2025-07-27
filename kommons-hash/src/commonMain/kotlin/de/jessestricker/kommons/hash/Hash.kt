package de.jessestricker.kommons.hash

/**
 * A hash function.
 *
 * @param D The type of the digest this hash function produces.
 */
public interface Hash<D : Digest> {
    /**
     * Process more data with this hash function.
     *
     * Reads bytes from [data] in the given range and updates the current state of this hash
     * function.
     */
    public fun process(data: ByteArray, startIndex: Int = 0, endIndex: Int = data.size)

    /**
     * Creates a digest of all the data processed by this hash function so far.
     *
     * This function does not change the current state of this hash function, so further data can be
     * processed by this hash function.
     */
    public fun digest(): D
}
