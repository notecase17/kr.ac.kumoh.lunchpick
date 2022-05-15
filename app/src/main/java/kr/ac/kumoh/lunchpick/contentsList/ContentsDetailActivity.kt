package kr.ac.kumoh.lunchpick.contentsList

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kr.ac.kumoh.lunchpick.R
import kr.ac.kumoh.lunchpick.databinding.ActivityContentsDetailBinding

class ContentsDetailActivity : AppCompatActivity() {
    lateinit var datas : ContentsModel
    private lateinit var binding: ActivityContentsDetailBinding
    private val menu: StoreViewModel by viewModels()
    private lateinit var adapter: MenusRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //추가
        binding = ActivityContentsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MenusRVAdapter(baseContext, menu)
        binding.MenuRv.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            adapter = this@ContentsDetailActivity.adapter
        }


        menu.storeMenuList.observe(this, {
            adapter.notifyDataSetChanged()
        })

        menu.requestStoreMenuList()

        val SERVER_URL = "https://csproject-qejmc.run.goorm.io/"
        val detail_store_image = findViewById<ImageView>(R.id.DetailImage)
        val detail_store_name = findViewById<TextView>(R.id.DetailName)
        val detail_store_name2 = findViewById<TextView>(R.id.DetailName2)
        val detail_store_addr = findViewById<TextView>(R.id.DetailAddress)
        val detail_store_num = findViewById<TextView>(R.id.DetailNum)


        datas = intent.getParcelableExtra("data")!!

        Toast.makeText(this,datas.image,Toast.LENGTH_SHORT).show()

        Glide.with(this).load("${SERVER_URL}images/${datas.image}").into(detail_store_image)
        detail_store_name.text = datas.storeName
        detail_store_name2.text = datas.storeName
        detail_store_addr.text = datas.storeAddress
        detail_store_num.text = "가게 번호 : ${datas.storeNum}"
    }
}