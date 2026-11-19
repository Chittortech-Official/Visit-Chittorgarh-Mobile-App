package `in`.visitchittorgarh.fort.tour.guide.data.repository

import android.content.Context
import android.content.SharedPreferences

class FavoritesManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("visit_chittorgarh_favorites", Context.MODE_PRIVATE)

    fun isFavorite(monumentId: String): Boolean {
        return prefs.getBoolean("fav_$monumentId", false)
    }

    fun toggleFavorite(monumentId: String): Boolean {
        val current = isFavorite(monumentId)
        prefs.edit().putBoolean("fav_$monumentId", !current).apply()
        return !current
    }

    fun getAllFavorites(): Set<String> {
        val set = mutableSetOf<String>()
        prefs.all.forEach { (key, value) ->
            if (key.startsWith("fav_") && value == true) {
                set.add(key.removePrefix("fav_"))
            }
        }
        return set
    }
}

