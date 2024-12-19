@file:OptIn(ExperimentalUnsignedTypes::class)

import kotlin.reflect.KClass

enum class PrimitiveType(val type: KClass<*>, val docName: String) {
    BYTE(type = Byte::class, docName = "byte"),
    SHORT(type = Short::class, docName = "short"),
    INT(type = Int::class, docName = "int"),
    LONG(type = Long::class, docName = "long"),
    FLOAT(type = Float::class, docName = "float"),
    DOUBLE(type = Double::class, docName = "double"),
    BOOLEAN(type = Boolean::class, docName = "boolean"),
    CHAR(type = Char::class, docName = "char"),
    UBYTE(type = UByte::class, docName = "unsigned byte"),
    USHORT(type = UShort::class, docName = "unsigned short"),
    UINT(type = UInt::class, docName = "unsigned int"),
    ULONG(type = ULong::class, docName = "unsigned long");

    companion object {
        val signedEntries = setOf(BYTE, SHORT, INT, LONG)
        val floatingPointEntries = setOf(FLOAT, DOUBLE)
        val unsignedEntries = setOf(UBYTE, USHORT, UINT, ULONG)
        val numericEntries = signedEntries + floatingPointEntries + unsignedEntries
        val builtinEntries = signedEntries + floatingPointEntries + setOf(BOOLEAN, CHAR)
    }

    val isSigned: Boolean
        get() = this in signedEntries

    val isFloatingPoint: Boolean
        get() = this in floatingPointEntries

    val isUnsigned: Boolean
        get() = this in unsignedEntries

    val isNumeric: Boolean
        get() = this in numericEntries

    val isBuiltin: Boolean
        get() = this in builtinEntries

    override fun toString(): String = type.simpleName!!

    val defaultValue: String
        get() =
            when {
                isNumeric -> "zero"
                this == BOOLEAN -> "`false`"
                this == CHAR -> "`'\\u0000'`"
                else -> error("no default value defined for $this")
            }
}
