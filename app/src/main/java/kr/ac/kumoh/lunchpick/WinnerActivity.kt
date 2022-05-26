package kr.ac.kumoh.lunchpick

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kr.ac.kumoh.lunchpick.databinding.ActivityWinnerBinding

class WinnerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWinnerBinding
    val url = "https://csproject-qejmc.run.goorm.io/StoreMenu"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent: Intent = intent
        val fd :food = intent.getSerializableExtra("winner") as food

        binding.winnerFoodName.text = "우    \"" + fd!!.name + "\"    승"
        Glide.with(this).load("${url}images/${fd!!.ima}").into(binding.WFimage)
        binding.WFText.text = fd!!.name
    }
}