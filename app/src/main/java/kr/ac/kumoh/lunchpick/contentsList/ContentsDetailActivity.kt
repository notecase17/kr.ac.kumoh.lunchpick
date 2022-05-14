package kr.ac.kumoh.lunchpick.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import kr.ac.kumoh.lunchpick.R
import kr.ac.kumoh.lunchpick.databinding.ActivityContentsDetailBinding
import kr.ac.kumoh.lunchpick.databinding.ActivityLoginBinding

class ContentsDetailActivity : AppCompatActivity() {
    lateinit var datas : ContentsModel
<<<<<<< HEAD
    private lateinit var binding: ActivityContentsDetailBinding

=======
>>>>>>> 309777ad89f4562ac6113e74ef65f92331960325
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityContentsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val slidePanel = binding.sliding_layout
        sliding_layout.addPanelSlideListener(PanelEventListener())  // 이벤트 리스너 추가

        val SERVER_URL = "https://csproject-qejmc.run.goorm.io/"
        val detail_store_image = findViewById<ImageView>(R.id.DetailImage)
        val detail_store_name = findViewById<TextView>(R.id.DetailName)
        val detail_store_addr = findViewById<TextView>(R.id.DetailAddress)
        val detail_store_num = findViewById<TextView>(R.id.DetailNum)

        datas = intent.getParcelableExtra("data")!!

        Toast.makeText(this,datas.image, Toast.LENGTH_SHORT).show()

        Glide.with(this).load("${SERVER_URL}images/${datas.image}").into(detail_store_image)
        detail_store_name.text = datas.storeName
        detail_store_addr.text = datas.storeAddress
        detail_store_num.text = "가게 번호 : ${datas.storeNum}"
    }

    // 이벤트 리스너
    inner class PanelEventListener : SlidingUpPanelLayout.PanelSlideListener {
        // 패널이 슬라이드 중일 때
        override fun onPanelSlide(panel: View?, slideOffset: Float) {

        }

        // 패널의 상태가 변했을 때
        override fun onPanelStateChanged(panel: View?, previousState: SlidingUpPanelLayout.PanelState?, newState: SlidingUpPanelLayout.PanelState?) {
            if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) { // 닫혔을 때

            } else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED) { // 열렸을 때

            }
        }
    }
}