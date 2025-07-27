package de.jessestricker.kommons.hash

public class Sha256 private constructor(private val impl: Hash<Sha256Digest>) :
    Hash<Sha256Digest> by impl {
    public constructor() : this(sha256Impl())
}

internal expect fun sha256Impl(): Hash<Sha256Digest>

public class Sha256Digest internal constructor(private val bytes: ByteArray) : Digest {
    override fun toByteArray(): ByteArray = bytes.copyOf()

    override fun toString(): String = bytes.toHexString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Sha256Digest
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

public fun ByteArray.toSha256Digest(): Sha256Digest {
    require(size == Sha256Digest.SIZE_BYTES)
    return Sha256Digest(copyOf())
}

public fun String.toSha256Digest(): Sha256Digest {
    val bytes = hexToByteArray()
    require(bytes.size == Sha256Digest.SIZE_BYTES)
    return Sha256Digest(bytes)
}
