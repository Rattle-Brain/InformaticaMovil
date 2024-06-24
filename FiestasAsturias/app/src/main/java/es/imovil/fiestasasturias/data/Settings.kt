package es.imovil.fiestasasturias.data

object Settings {
    const val MOBILE_DATA: String = "mobile_data"
    const val LANG: String = "language"
    const val USE_DEFAULT_THEME: String = "system_theme"
    const val DARK_THEME: String = "dark_theme"
}

enum class AllowUseMobileData{
    ANY,
    WIFI_ONLY
}
enum class Language{
    ES,
    EN
}

data class SettingsParameters(
    val useMobileData: String,
    val language: String,
    val useDefaultSystemTheme: Boolean,
    val nightTheme: Boolean
)