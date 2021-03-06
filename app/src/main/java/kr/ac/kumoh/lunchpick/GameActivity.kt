package kr.ac.kumoh.lunchpick

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kr.ac.kumoh.lunchpick.databinding.ActivityGameBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.lang.Math.*
import kotlin.math.round

data class food (
    val name: String,
    val ima : String
) : Serializable

class GameActivity : AppCompatActivity() {
    companion object{
        const val QUEUE_TAG = "VolleyRequest"
    }
    private lateinit var binding: ActivityGameBinding
    lateinit var mqueue: RequestQueue

    override fun  onStop(){
        super.onStop()
        mqueue.cancelAll(QUEUE_TAG)
    }
    fun requestMenu() {
        val url = "https://csproject-qejmc.run.goorm.io/Menu"
        val request = JsonArrayRequest(
            Request.Method.GET,
            url, null,
            { response ->
                for (i in 0 until response.length()){
                    val item = response[i] as JSONObject
                    val name = item.getString("menu")
                    val ima = item.getString("image")
                    val fd = food(name,ima)
                    getit[i] = fd
                }
                makesel()
            },
            { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            })
        request.tag = QUEUE_TAG
        mqueue.add(request)
    }
    fun requestStore() {
        val url = "https://csproject-qejmc.run.goorm.io/Restaurant"
        val request = JsonArrayRequest(
            Request.Method.GET,
            url, null,
            { response ->
                for (i in 0 until response.length()){
                    val item = response[i] as JSONObject
                    val name = item.getString("store_name")
                    val ima = item.getString("store_image")
                    val fd = food(name,ima)
                    getit[i] = fd
                }
                makesel()
            },
            { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            })
        request.tag = QUEUE_TAG
        mqueue.add(request)
    }

    val SERVER_URL = "https://csproject-qejmc.run.goorm.io/"
    var idnt = Array<food?>(32) {null}
    var getit = Array<food?>(999) {null}
    var count = 0
    var c_count = 0
    var round = 0
    var n_round = 1
    var size = 16
    var i = true
    var j = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mqueue = Volley.newRequestQueue(this)
        c_count = size
        round = size

        val type = intent.getStringExtra("cupType")
        when(type){
            "menu"->{requestMenu()}
            "restaurant"->{requestStore()}
        }

        binding.btnFoodImg1.setOnClickListener {
            // Toast.makeText(this, binding.FItext.text.toString(), Toast.LENGTH_SHORT).show()
            val fd = idnt[count-2]
            idnt[c_count++]=fd
            click()
        }
        binding.btnFoodImg2.setOnClickListener {
            //Toast.makeText(this, binding.SItext.text.toString(), Toast.LENGTH_SHORT).show()
            val fd = idnt[count-1]
            idnt[c_count++]=fd
            click()
        }
        binding.ivFavorite1.setOnClickListener {
            if(i){
                binding.ivFavorite1.setImageResource(R.drawable.ic_baseline_star_24)
                i = false
                Toast.makeText(this, "???????????? ??????!", Toast.LENGTH_SHORT).show()
            }
            else{
                binding.ivFavorite1.setImageResource(R.drawable.ic_baseline_star_border_24)
                i = true
                Toast.makeText(this, "???????????? ??????!", Toast.LENGTH_SHORT).show()
            }
        }
        binding.ivFavorite2.setOnClickListener {
            if(j){
                binding.ivFavorite2.setImageResource(R.drawable.ic_baseline_star_24)
                j=false
                Toast.makeText(this, "???????????? ??????!", Toast.LENGTH_SHORT).show()
            }
            else{
                binding.ivFavorite2.setImageResource(R.drawable.ic_baseline_star_border_24)
                j=true
                Toast.makeText(this, "???????????? ??????!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun makesel(){
        for(i in 0 until size){
            idnt[i] = getit[i]
        }
        Glide.with(this).load("${SERVER_URL}images/${idnt[count]!!.ima}").into(binding.btnFoodImg1)
        binding.tvFoodName1.text = idnt[count++]!!.name
        Glide.with(this).load("${SERVER_URL}images/${idnt[count]!!.ima}").into(binding.btnFoodImg2)
        binding.tvFoodName2.text = idnt[count++]!!.name
        binding.tvWorldcupRound.text = round.toString() + "??? " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"
    }


    fun click(){
        n_round++
        if(round<n_round*2){
            round/=2
            n_round=1
        }
        if(idnt[count+1]==null){
            val intent = Intent(this, WinnerActivity::class.java)
            intent.putExtra("winner",idnt[count])
            startActivity(intent)
            finish()
        }
        else{
            binding.ivFavorite1.setImageResource(R.drawable.ic_baseline_star_border_24)
            binding.ivFavorite2.setImageResource(R.drawable.ic_baseline_star_border_24)
            Glide.with(this).load("${SERVER_URL}images/${idnt[count]!!.ima}").into(binding.btnFoodImg1)
            binding.tvFoodName1.text = idnt[count++]!!.name
            Glide.with(this).load("${SERVER_URL}images/${idnt[count]!!.ima}").into(binding.btnFoodImg2)
            binding.tvFoodName2.text = idnt[count++]!!.name
            when (round) {
                4 -> {
                    binding.tvWorldcupRound.text = "????????? " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"
                }
                2 -> {
                    binding.tvWorldcupRound.text = "??????"
                }
                else -> {
                    binding.tvWorldcupRound.text = round.toString() + "??? " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"
                }
            }
        }
    }
}