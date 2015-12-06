package pl.droidcon.dangerousboa

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson

class SoundPrefs(var rock: Boolean, var electro: Boolean, var classical: Boolean, var rap: Boolean)

fun Context.soundPrefs(): SoundPrefs? {
    val json = PreferenceManager.getDefaultSharedPreferences(this).getString("soundPrefs", null)
    val soundPrefs = Gson().fromJson(json, SoundPrefs::class.java)
    return soundPrefs
}

fun Context.setSoundPrefs(prefs: SoundPrefs) {
    val json = Gson().toJson(prefs)
    PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString("soundPrefs", json)
            .apply()
}
