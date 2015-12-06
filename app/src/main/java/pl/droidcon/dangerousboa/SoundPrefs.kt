package pl.droidcon.dangerousboa

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import java.util.*

class SoundPrefs(var rock: Boolean, var electro: Boolean, var classical: Boolean, var rap: Boolean)

fun Context.soundPrefs(): SoundPrefs {
    val json = PreferenceManager.getDefaultSharedPreferences(this).getString("soundPrefs", null)
    val soundPrefs: SoundPrefs? = Gson().fromJson(json, SoundPrefs::class.java)
    if (soundPrefs != null) {
        return soundPrefs
    } else {
        val r = Random()
        val new = SoundPrefs(r.nextBoolean(), r.nextBoolean(), r.nextBoolean(), r.nextBoolean())
        setSoundPrefs(new)
        return new
    }
}

fun Context.setSoundPrefs(prefs: SoundPrefs) {
    val json = Gson().toJson(prefs)
    PreferenceManager.getDefaultSharedPreferences(this)
            .edit()
            .putString("soundPrefs", json)
            .apply()
}
