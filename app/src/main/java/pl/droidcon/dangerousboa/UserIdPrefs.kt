package pl.droidcon.dangerousboa

import android.content.Context
import android.preference.PreferenceManager
import com.google.gson.Gson
import java.util.*

val Context.userId: String get() {
    val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this)
    val userId = sharedPrefs.getString("userId", null)
    if (userId != null) {
        return userId
    } else {
        val new = UUID.randomUUID().toString()
        sharedPrefs.edit().putString("userId", new)
        return new
    }
}
