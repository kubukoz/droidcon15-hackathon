package pl.droidcon.dangerousboa

import android.content.Intent
import android.os.Bundle
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

    override fun onResume() {
        super.onResume()
        if (RelayrSdk.isUserLoggedIn())
            RelayrSdk.getUser().observeOn(AndroidSchedulers.mainThread()).subscribe {
                RelayrSdk.getUserApi().getDevices(it.id).subscribe { devices ->
                    devices.forEach {
                        it.subscribeToCloudReadings()
                                .map { it.value as Double }
                                //                            .filter { it > 1 }
                                .subscribe({
                                    Log.e("tag", "value: " + it)
                                    startActivity(Intent(this, MusicActivity::class.java))
                                }, {
                                    Log.e("tag", "error", it)
                                })
                    }
                }
            }
    }
}
