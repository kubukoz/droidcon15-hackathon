package pl.droidcon.dangerousboa

import android.app.Application
import com.firebase.client.Firebase

class SoundCrowdApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.setAndroidContext(this)
    }
}
