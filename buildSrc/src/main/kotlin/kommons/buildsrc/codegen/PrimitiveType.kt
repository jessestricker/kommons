package kommons.buildsrc.codegen

enum class PrimitiveType {
    BYTE,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    CHAR,
    UBYTE,
    USHORT,
    UINT,
    ULONG;

    override fun toString(): String =
        when (this) {
            BYTE -> "Byte"
            SHORT -> "Short"
            INT -> "Int"
            LONG -> "Long"
            FLOAT -> "Float"
            DOUBLE -> "Double"
            BOOLEAN -> "Boolean"
            CHAR -> "Char"
            UBYTE -> "UByte"
            USHORT -> "UShort"
            UINT -> "UInt"
            ULONG -> "ULong"
        }

    val docName: String
        get() =
            when (this) {
                BYTE -> "byte"
                SHORT -> "short"
                INT -> "int"
                LONG -> "long"
                FLOAT -> "float"
                DOUBLE -> "double"
                BOOLEAN -> "boolean"
                CHAR -> "char"
                UBYTE -> "unsigned byte"
                USHORT -> "unsigned short"
                UINT -> "unsigned int"
                ULONG -> "unsigned long"
            }

    val docNamePlural: String
        get() = "${docName}s"

    val defaultValue: String
        get() =
            when (this) {
                BYTE -> "0"
                SHORT -> "0"
                INT -> "0"
                LONG -> "0L"
                FLOAT -> "0.0f"
                DOUBLE -> "0.0"
                BOOLEAN -> "false"
                CHAR -> "'\\u0000'"
                UBYTE -> "0u"
                USHORT -> "0u"
                UINT -> "0u"
                ULONG -> "0uL"
            }

    val isUnsigned: Boolean
        get() = this in unsignedEntries

    companion object {
        val unsignedEntries = setOf(UBYTE, USHORT, UINT, ULONG)
    }
}
