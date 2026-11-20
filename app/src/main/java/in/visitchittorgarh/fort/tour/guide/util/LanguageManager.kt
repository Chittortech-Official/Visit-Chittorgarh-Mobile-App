package `in`.visitchittorgarh.fort.tour.guide.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import java.util.Locale

data class AppLanguage(
    val code: String,
    val nativeName: String,
    val englishName: String,
    val flag: String,
    val locale: Locale
)

object LanguageManager {
    private const val PREFS_NAME = "visitchittorgarh_lang_prefs"
    private const val KEY_LANGUAGE = "selected_language"

    val SUPPORTED_LANGUAGES = listOf(
        AppLanguage("en", "English", "English", "🇬🇧", Locale.ENGLISH),
        AppLanguage("hi", "हिन्दी", "Hindi", "🇮🇳", Locale.forLanguageTag("hi-IN")),
        AppLanguage("fr", "Français", "French", "🇫🇷", Locale.FRENCH),
        AppLanguage("de", "Deutsch", "German", "🇩🇪", Locale.GERMAN),
        AppLanguage("ur", "اردو", "Urdu", "🇵🇰", Locale.forLanguageTag("ur"))
    )

    fun getSelectedLanguageCode(context: Context): String {
        val appLocales = AppCompatDelegate.getApplicationLocales()
        if (!appLocales.isEmpty) {
            val tag = appLocales[0]?.language
            if (!tag.isNullOrEmpty() && SUPPORTED_LANGUAGES.any { it.code == tag }) {
                return tag
            }
        }
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_LANGUAGE, null) ?: "en"
    }

    fun getSelectedLanguage(context: Context): AppLanguage {
        val code = getSelectedLanguageCode(context)
        return SUPPORTED_LANGUAGES.firstOrNull { it.code == code } ?: SUPPORTED_LANGUAGES[0]
    }

    fun setLanguage(context: Context, languageCode: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply()

        val appLocale = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(appLocale)
    }

    fun getLocale(context: Context): Locale {
        return getSelectedLanguage(context).locale
    }
}
