plugins {
    `kotlin-dsl`

    alias(libs.plugins.ktfmt)
}

ktfmt {
    kotlinLangStyle()
}
