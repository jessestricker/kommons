package de.jessestricker.kommons.hash

internal actual fun shaThree512Impl(): Hash<ShaThree512Digest> =
    object : JvmHash<ShaThree512Digest>("SHA3-512") {
        override fun newDigest(bytes: ByteArray): ShaThree512Digest {
            return ShaThree512Digest(bytes)
        }
    }
