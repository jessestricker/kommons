package de.jessestricker.kommons.hash

internal actual fun shaThree256Impl(): Hash<ShaThree256Digest> =
    object : JvmHash<ShaThree256Digest>("SHA3-256") {
        override fun newDigest(bytes: ByteArray): ShaThree256Digest {
            return ShaThree256Digest(bytes)
        }
    }
