package de.jessestricker.kommons.hash

public class ShaThree512 private constructor(private val impl: Hash<ShaThree512Digest>) :
    Hash<ShaThree512Digest> by impl {
    public constructor() : this(shaThree512Impl())
}

internal expect fun shaThree512Impl(): Hash<ShaThree512Digest>

public class ShaThree512Digest internal constructor(private val bytes: ByteArray) : Digest {
    override fun toByteArray(): ByteArray = bytes.copyOf()

    override fun toString(): String = bytes.toHexString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as ShaThree512Digest
        return bytes.contentEquals(other.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    public companion object {
        public const val SIZE_BITS: Int = 512
        public const val SIZE_BYTES: Int = 64
    }
}

public fun ByteArray.toShaThree512Digest(): ShaThree512Digest {
    require(size == ShaThree512Digest.SIZE_BYTES)
    return ShaThree512Digest(copyOf())
}

public fun String.toShaThree512Digest(): ShaThree512Digest {
    val bytes = hexToByteArray()
    require(bytes.size == ShaThree512Digest.SIZE_BYTES)
    return ShaThree512Digest(bytes)
}
