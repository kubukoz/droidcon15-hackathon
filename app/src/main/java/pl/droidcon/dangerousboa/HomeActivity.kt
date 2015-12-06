package pl.droidcon.dangerousboa

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import io.relayr.android.RelayrSdk
import rx.android.schedulers.AndroidSchedulers

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RelayrSdkInitializer.init(this)

        if (RelayrSdk.isUserLoggedIn())
            showLoggedUi()
        else showNotLoggedUi()
    }

    private fun showLoggedUi() {
        setContentView(R.layout.activity_home)
        val tv: TextView = findViewById(R.id.hello_view) as TextView
        RelayrSdk.getUser().observeOn(AndroidSchedulers.mainThread()).subscribe { user ->
            tv.text = "Hello ${user.name}!"
        }
    }

    private fun showNotLoggedUi() {
        setContentView(R.layout.view_login)
        findViewById(R.id.login_button).setOnClickListener {
            RelayrSdk.logIn(this)
        }
    }

    override fun onResume() {
        super.onResume()
        if (RelayrSdk.isUserLoggedIn()) showLoggedUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            R.id.action_settings -> {
                startActivity(Intent(this, SoundPreferencesActivity::class.java))
                true
            }
            else -> false
        }
    }
}


object RelayrSdkInitializer {
    fun init(context: Context) = RelayrSdk.Builder(context).inMockMode(false).build();
}