package pl.droidcon.dangerousboa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        findViewById(R.id.login_login).setOnClickListener {
            startActivity(Intent(this, LoginProgressActivity::class.java))
        }
        findViewById(R.id.login_prefs).setOnClickListener {
            startActivity(Intent(this, SoundPreferencesActivity::class.java))
        }
    }
}
