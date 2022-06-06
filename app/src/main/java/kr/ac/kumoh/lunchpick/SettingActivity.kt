package kr.ac.kumoh.lunchpick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.lunchpick.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}