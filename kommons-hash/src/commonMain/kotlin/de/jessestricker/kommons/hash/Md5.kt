package de.jessestricker.kommons.hash

public class Md5 private constructor(private val impl: Hash<Md5Digest>) : Hash<Md5Digest> by impl {
    public constructor() : this(md5Impl())
}

internal expect fun md5Impl(): Hash<Md5Digest>

public class Md5Digest internal constructor(private val bytes: ByteArray) : Digest {
    override fun toByteArray(): ByteArray = bytes.copyOf()

    override fun toString(): String = bytes.toHexString()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Md5Digest
        return bytes.contentEquals(other.bytes)
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }

    public companion object {
        public const val SIZE_BITS: Int = 128
        public const val SIZE_BYTES: Int = 16
    }
}

public fun ByteArray.toMd5Digest(): Md5Digest {
    require(size == Md5Digest.SIZE_BYTES)
    return Md5Digest(copyOf())
}

public fun String.toMd5Digest(): Md5Digest {
    val bytes = hexToByteArray()
    require(bytes.size == Md5Digest.SIZE_BYTES)
    return Md5Digest(bytes)
}
