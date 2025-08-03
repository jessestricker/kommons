package de.jessestricker.kommons.hash

import java.security.MessageDigest

internal abstract class JvmHash<D : Digest>(algorithmName: String) : Hash<D> {
    private val state = MessageDigest.getInstance(algorithmName)

    override fun process(data: ByteArray, startIndex: Int, endIndex: Int) {
        state.digest(data, /* offset= */ startIndex, /* len= */ endIndex - startIndex)
    }

    override fun digest(): D {
        val clonedState = state.clone() as MessageDigest
        val digestBytes = clonedState.digest()
        return newDigest(digestBytes)
    }

    protected abstract fun newDigest(bytes: ByteArray): D
}
