package de.jessestricker.kommons.hash

internal actual fun md5Impl(): Hash<Md5Digest> =
    object : JvmHash<Md5Digest>("MD5") {
        override fun newDigest(bytes: ByteArray): Md5Digest {
            return Md5Digest(bytes)
        }
    }
