package kommons.buildsrc.codegen

enum class PrimitiveType {
    Byte,
    Short,
    Int,
    Long,
    Float,
    Double,
    Boolean,
    Char,
    UByte,
    UShort,
    UInt,
    ULong;

    val defaultValue: String
        get() =
            when (this) {
                Byte -> "0"
                Short -> "0"
                Int -> "0"
                Long -> "0L"
                Float -> "0.0f"
                Double -> "0.0"
                Boolean -> "false"
                Char -> "'\\u0000'"
                UByte -> "0u"
                UShort -> "0u"
                UInt -> "0u"
                ULong -> "0uL"
            }

    companion object {
        val standardEntries = sortedSetOf(Byte, Short, Int, Long, Float, Double, Boolean, Char)
    }
}
