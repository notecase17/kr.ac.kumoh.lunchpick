package kr.ac.kumoh.lunchpick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.android.volley.RequestQueue
import kr.ac.kumoh.lunchpick.contentsList.VolleyRequest
import kr.ac.kumoh.lunchpick.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    companion object {
        const val QUEUE_TAG = "VolleyRequest"
    }

    private lateinit var mQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQueue = VolleyRequest.getInstance(this.applicationContext).requestQueue

        val handler: Handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    override fun onStop(){
        super.onStop()
        mQueue.cancleAll(QUEUE_TAG)
    }
}