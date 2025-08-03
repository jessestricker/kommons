package de.jessestricker.kommons.hash

public class ShaThree256 private constructor(private val impl: Hash<ShaThree256Digest>) :
    Hash<ShaThree256Digest> by impl {
    public constructor() : this(shaThree256Impl())
}

internal expect fun shaThree256Impl(): Hash<ShaThree256Digest>

public class ShaThree256Digest internal constructor(private val bytes: ByteArray) : Digest {
    override fun toByteArray(): ByteArray = bytes.copyOf()

    override fun toString(): String = bytes.toHexString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as ShaThree256Digest
        return bytes.contentEquals(other.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    public companion object {
        public const val SIZE_BITS: Int = 256
        public const val SIZE_BYTES: Int = 32
    }
}

public fun ByteArray.toShaThree256Digest(): ShaThree256Digest {
    require(size == ShaThree256Digest.SIZE_BYTES)
    return ShaThree256Digest(copyOf())
}

public fun String.toShaThree256Digest(): ShaThree256Digest {
    val bytes = hexToByteArray()
    require(bytes.size == ShaThree256Digest.SIZE_BYTES)
    return ShaThree256Digest(bytes)
}
