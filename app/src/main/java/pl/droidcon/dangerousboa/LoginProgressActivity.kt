package pl.droidcon.dangerousboa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.relayr.java.model.TransmitterDevice

class LoginProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RelayrSdkInitializer.init(this)
        TransmitterDevice("092fda27-a675-463e-8197-654436a65a3f", "412069", "d3d7d57b-dd5c-408f-a831-cc4ad2cf4fc5", "light", "a7ec1b21-8582-4304-b1cf-15a1fc66d1e8").subscribeToCloudReadings().subscribe({
            val value = it.value
            Log.e("tag", "value: " + value)
        }, {
            Log.e("tag", "error", it)
        })
//        RelayrSdk.logIn(this)
    }

    override fun onResume() {
        super.onResume()
        /*RelayrSdk.getUser().observeOn(AndroidSchedulers.mainThread()).subscribe {
            user ->
            RelayrSdk.getUserApi().getDevices(user.id).observeOn(AndroidSchedulers.mainThread()).subscribe{
                it.filter{it.name == "light"}.forEach {
                    device -> println(device.toString())
                }
            }

        }*/
    }
}
