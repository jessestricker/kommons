package de.jessestricker.kommons.hash

internal actual fun sha256Impl(): Hash<Sha256Digest> =
    object : JvmHash<Sha256Digest>("SHA-256") {
        override fun newDigest(bytes: ByteArray): Sha256Digest {
            return Sha256Digest(bytes)
        }
    }
