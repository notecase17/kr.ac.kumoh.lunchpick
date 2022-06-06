package kr.ac.kumoh.lunchpick

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kr.ac.kumoh.lunchpick.SharedPreference.LocalUser
import kr.ac.kumoh.lunchpick.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    lateinit var fadeInAnim : Animation
    lateinit var fadeOutAnim : Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        // 상태바를 없앰
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(binding.root)

        fadeInAnim = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        fadeOutAnim = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        val foodImageDrawableArray = getResources().obtainTypedArray(R.array.food_img)
        val length = foodImageDrawableArray.length()

        for(i in 0..5){
            binding.TopImage.setImageDrawable(foodImageDrawableArray.getDrawable(i))
            binding.BottomImage.setImageDrawable(foodImageDrawableArray.getDrawable(length - i))
            binding.TopImage.startAnimation(fadeInAnim)
            binding.BottomImage.startAnimation(fadeOutAnim)
        }

        val handler: Handler = Handler()
        handler.postDelayed({
            val intent: Intent
            if (LocalUser.prefs.getBool("logon", false)){
                intent = Intent(this, MainActivity::class.java)
            } else {
                intent = Intent(this, LoginActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 3000)
    }
}