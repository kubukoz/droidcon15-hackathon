package pl.droidcon.dangerousboa

import android.app.Activity
import android.os.Bundle
import android.widget.CheckBox

class SoundPreferencesActivity : Activity() {

    val prefs: SoundPrefs by lazy { soundPrefs() }

    val pref1 by lazy { findViewById(R.id.sound_preference_1) as CheckBox }
    val pref2 by lazy { findViewById(R.id.sound_preference_2) as CheckBox }
    val pref3 by lazy { findViewById(R.id.sound_preference_3) as CheckBox }
    val pref4 by lazy { findViewById(R.id.sound_preference_4) as CheckBox }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sound_preferences)
        val data = prefs
        pref1.isChecked = data.rock
        pref2.isChecked = data.electro
        pref3.isChecked = data.classical
        pref4.isChecked = data.rap

        listOf(pref1, pref2, pref3, pref4).forEach {
            it.setOnCheckedChangeListener { view, state ->
                data.rock = pref1.isChecked
                data.electro = pref2.isChecked
                data.classical = pref3.isChecked
                data.rap = pref4.isChecked
                setSoundPrefs(data)
            }
        }
    }
}
