package de.jessestricker.kommons.hash

/** The output of a hash function. */
public interface Digest {
    /** The value of this digest as a byte array. */
    public fun toByteArray(): ByteArray

    /** The value of this digest in a hexadecimal format. */
    override fun toString(): String
}
