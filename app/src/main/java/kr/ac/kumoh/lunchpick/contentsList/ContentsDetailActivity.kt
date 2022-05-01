package kr.ac.kumoh.lunchpick.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kr.ac.kumoh.lunchpick.R
import kr.ac.kumoh.lunchpick.databinding.ActivityContentsDetailBinding
import kr.ac.kumoh.lunchpick.databinding.ActivityLoginBinding

class ContentsDetailActivity : AppCompatActivity() {
    lateinit var datas : ContentsModel
    private lateinit var binding: ActivityContentsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContentsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detail_store_image = findViewById<ImageView>(R.id.DetailImage)
        val detail_store_name = findViewById<TextView>(R.id.DetailName)

        datas = intent.getParcelableExtra("data")!!

        Glide.with(this).load(datas.imageUrl).into(detail_store_image)
        detail_store_name.text = datas.storeName
    }
}