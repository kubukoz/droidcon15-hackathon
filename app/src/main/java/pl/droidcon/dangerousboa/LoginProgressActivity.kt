package pl.droidcon.dangerousboa

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.relayr.android.RelayrSdk
import io.relayr.java.model.TransmitterDevice

class LoginProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RelayrSdkInitializer.init(this)
        TransmitterDevice("092fda27-a675-463e-8197-654436a65a3f", "f.x-UR.1Gmb-Kooo98hgZT5i6yp5e4QH", "d3d7d57b-dd5c-408f-a831-cc4ad2cf4fc5", "light", "a7ec1b21-8582-4304-b1cf-15a1fc66d1e8").subscribeToCloudReadings().subscribe({
            val value = it.value
            Log.e("tag", "value: " + value)
        }, {
            Log.e("tag", "error", it)
        })
    }
}
