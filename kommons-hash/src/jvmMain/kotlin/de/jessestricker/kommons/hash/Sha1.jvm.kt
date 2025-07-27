package de.jessestricker.kommons.hash

internal actual fun sha1Impl(): Hash<Sha1Digest> =
    object : JvmHash<Sha1Digest>("SHA-1") {
        override fun newDigest(bytes: ByteArray): Sha1Digest {
            return Sha1Digest(bytes)
        }
    }
