package pl.droidcon.dangerousboa

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.relayr.android.RelayrSdk
import rx.android.schedulers.AndroidSchedulers

class LoginProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_progress)
        RelayrSdkInitializer.init(this)
        if (!RelayrSdk.isUserLoggedIn())
            RelayrSdk.logIn(this)
    }

    val handler = Handler()
    val openMusic = Runnable {
        startActivity(Intent(this, MusicActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        if (RelayrSdk.isUserLoggedIn()) {
            RelayrSdk.getUser().observeOn(AndroidSchedulers.mainThread()).subscribe {
                RelayrSdk.getUserApi().getDevices(it.id).subscribe { devices ->
                    devices.forEach {
                        it.subscribeToCloudReadings()
                                .doOnNext { Log.e("tag", "value: " + it) }
                                .filter { it.meaning == "proximity" }
                                .map { it.value as Double }
                                .filter { it > 200 }
                                .take(1)
                                .subscribe({
                                    Log.e("tag", "value: " + it)
                                    startActivity(Intent(this, MusicActivity::class.java))
                                    handler.removeCallbacks(openMusic)
                                }, {
                                    Log.e("tag", "error", it)
                                })
                    }
                }
            }
            handler.postDelayed(openMusic, 10000)
        }
    }
}
