package de.jessestricker.kommons.hash

public class Sha512 private constructor(private val impl: Hash<Sha512Digest>) :
    Hash<Sha512Digest> by impl {
    public constructor() : this(sha512Impl())
}

internal expect fun sha512Impl(): Hash<Sha512Digest>

public class Sha512Digest internal constructor(private val bytes: ByteArray) : Digest {
    override fun toByteArray(): ByteArray = bytes.copyOf()

    override fun toString(): String = bytes.toHexString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Sha512Digest
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

public fun ByteArray.toSha512Digest(): Sha512Digest {
    require(size == Sha512Digest.SIZE_BYTES)
    return Sha512Digest(copyOf())
}

public fun String.toSha512Digest(): Sha512Digest {
    val bytes = hexToByteArray()
    require(bytes.size == Sha512Digest.SIZE_BYTES)
    return Sha512Digest(bytes)
}
