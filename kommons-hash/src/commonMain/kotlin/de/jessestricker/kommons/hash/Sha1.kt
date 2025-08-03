package de.jessestricker.kommons.hash

public class Sha1 private constructor(private val impl: Hash<Sha1Digest>) :
    Hash<Sha1Digest> by impl {
    public constructor() : this(sha1Impl())
}

internal expect fun sha1Impl(): Hash<Sha1Digest>

public class Sha1Digest internal constructor(private val bytes: ByteArray) : Digest {
    override fun toByteArray(): ByteArray = bytes.copyOf()

    override fun toString(): String = bytes.toHexString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Sha1Digest
        return bytes.contentEquals(other.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    public companion object {
        public const val SIZE_BITS: Int = 160
        public const val SIZE_BYTES: Int = 20
    }
}

public fun ByteArray.toSha1Digest(): Sha1Digest {
    require(size == Sha1Digest.SIZE_BYTES)
    return Sha1Digest(copyOf())
}

public fun String.toSha1Digest(): Sha1Digest {
    val bytes = hexToByteArray()
    require(bytes.size == Sha1Digest.SIZE_BYTES)
    return Sha1Digest(bytes)
}
