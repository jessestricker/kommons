package de.jessestricker.kommons.hash

internal actual fun sha512Impl(): Hash<Sha512Digest> =
    object : JvmHash<Sha512Digest>("SHA-512") {
        override fun newDigest(bytes: ByteArray): Sha512Digest {
            return Sha512Digest(bytes)
        }
    }
