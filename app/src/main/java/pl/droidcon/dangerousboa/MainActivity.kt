package pl.droidcon.dangerousboa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
            finish()
        }, 2000)
    }
}