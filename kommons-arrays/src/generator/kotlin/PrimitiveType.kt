enum class PrimitiveType(val humanName: String, val javaType: String) {
    Boolean("boolean", "boolean"),
    Char("char", "char"),
    Byte("byte", "byte"),
    Short("short", "short"),
    Int("int", "int"),
    Long("long", "long"),
    Float("float", "float"),
    Double("double", "double"),
    UByte("unsigned byte", "byte"),
    UShort("unsigned short", "short"),
    UInt("unsigned int", "int"),
    ULong("unsigned long", "long");

    companion object {
        private val floatingPointEntries = setOf(Float, Double)
        private val unsignedEntries = setOf(UByte, UShort, UInt, ULong)

        val PrimitiveType.isFloatingPoint
            get() = this in floatingPointEntries

        val PrimitiveType.isUnsigned
            get() = this in unsignedEntries
    }
}
