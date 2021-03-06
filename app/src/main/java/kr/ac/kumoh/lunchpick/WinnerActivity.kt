package kr.ac.kumoh.lunchpick

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kr.ac.kumoh.lunchpick.contentsList.ContentsListActivity
import kr.ac.kumoh.lunchpick.contentsList.ContentsRVAdapter
import kr.ac.kumoh.lunchpick.databinding.ActivityWinnerBinding

class WinnerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWinnerBinding
    val url = "https://csproject-qejmc.run.goorm.io/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent: Intent = intent
        val fd :food = intent.getSerializableExtra("winner") as food

        binding.ivFoodname.text = fd.name
        Glide.with(this).load("${url}images/${fd!!.ima}").into(binding.ivFoodImg)

        binding.FindRestaurant.setOnClickListener {
            val intent = Intent(this, ContentsListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}