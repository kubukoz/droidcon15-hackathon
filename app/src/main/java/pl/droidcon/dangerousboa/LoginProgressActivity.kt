package pl.droidcon.dangerousboa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.relayr.java.model.TransmitterDevice

class LoginProgressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RelayrSdkInitializer.init(this)
        TransmitterDevice("9e5d9ac5-96fc-459a-a3d2-8946b6e2c961", "f.x-UR.1Gmb-Kooo98hgZT5i6yp5e4QH", "d3d7d57b-dd5c-408f-a831-cc4ad2cf4fc5", "light", "a7ec1b21-8582-4304-b1cf-15a1fc66d1e8").subscribeToCloudReadings()
                .filter { it.meaning == "proximity" }
                .map { it.value as Double }
                .filter { it > 200 }
                .take(1)
                .subscribe({
                    Log.e("tag", "value: " + it)
                    startActivity(Intent(this, MusicActivity::class.java))
                }, {
                    Log.e("tag", "error", it)
                })
    }
}
