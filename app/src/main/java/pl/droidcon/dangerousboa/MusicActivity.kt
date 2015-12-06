package pl.droidcon.dangerousboa

import android.media.MediaPlayer
import android.os.Bundle
import android.os.UserManager
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.firebase.client.DataSnapshot
import com.firebase.client.Firebase
import com.firebase.client.FirebaseError
import com.firebase.client.ValueEventListener

class MusicActivity : AppCompatActivity() {
    var selectedSong = 0

    val player by lazy { MediaPlayer.create(this, getSong()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if ((getSystemService(USER_SERVICE) as UserManager).isUserAGoat)
            Toast.makeText(this, "You are a goat!", Toast.LENGTH_SHORT).show()

        selectedSong = 0

        player.setVolume(1f, 1f)
        player.start()

        val firebase = Firebase("https://incandescent-inferno-5098.firebaseio.com/users")
        firebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                val allUsersPrefs = data.children.map {
                    val list = it.value as List<Boolean>
                    SoundPrefs(list[0], list[1], list[2], list[3])
                }
            }

            override fun onCancelled(error: FirebaseError) {
            }
        })
        firebase.updateChildren(mapOf(userId to soundPrefs().asList()))
    }

    fun getSong() = when (selectedSong) {
        0 -> R.raw.classical
        1 -> R.raw.electro
        2 -> R.raw.rap
        else -> R.raw.rock
    }

    override fun onDestroy() {
        player.stop()
        super.onDestroy()
    }
}
