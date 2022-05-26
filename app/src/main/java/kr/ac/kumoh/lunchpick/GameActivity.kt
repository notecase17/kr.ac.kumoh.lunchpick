package kr.ac.kumoh.lunchpick

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kr.ac.kumoh.lunchpick.databinding.ActivityGameBinding
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

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
    var mResult: JSONObject? = null
    var mArray = ArrayList<food>()

    override fun  onStop(){
        super.onStop()
        mqueue.cancelAll(QUEUE_TAG)
    }
    fun requestMenu() {
        val url = "https://csproject-qejmc.run.goorm.io/Menu"
        val request = JsonObjectRequest(
            Request.Method.GET,
            url, null,
            { response ->
                mResult = response
                makesel() },
            { error ->
                Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
            })

        request.tag = QUEUE_TAG
        mqueue.add(request)
    }

    fun makesel() {
        val items: JSONArray = mResult?.getJSONArray("menu_ID") ?: return
        mArray.clear()
        for (i in 0 until items!!.length()){
            val item = items[i] as JSONObject
            val name = item.getString("menu")
            val ima = item.getString("image")
            mArray.add(food(name,ima))
            idnt[i] = mArray[i]
        }
    }
    val SERVER_URL = "https://csproject-qejmc.run.goorm.io/"
    var idnt = Array<food?>(32) {null}
    var count = 0
    var c_count = 0
    var round = 0
    var n_round = 1
    var size = 8

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mqueue = Volley.newRequestQueue(this)
        requestMenu()

        c_count = size
        round = size

        Glide.with(this).load("${SERVER_URL}image/${idnt[count]!!.ima}").into(binding.FirstImage)
        binding.FItext.text = idnt[count++]!!.name
        Glide.with(this).load("${SERVER_URL}image/${idnt[count]!!.ima}").into(binding.secondImage)
        binding.SItext.text = idnt[count++]!!.name
        binding.progressing.text = round.toString() + "강 " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"

        binding.FirstImage.setOnClickListener {
            // Toast.makeText(this, binding.FItext.text.toString(), Toast.LENGTH_SHORT).show()
            val fd = idnt[count-2]
            idnt[c_count++]=fd
            click()
        }
        binding.secondImage.setOnClickListener {
            //Toast.makeText(this, binding.SItext.text.toString(), Toast.LENGTH_SHORT).show()
            val fd = idnt[count-1]
            idnt[c_count++]=fd
            click()
        }
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
            Glide.with(this).load("${SERVER_URL}image/${idnt[count]!!.ima}").into(binding.FirstImage)
            binding.FItext.text = idnt[count++]!!.name
            Glide.with(this).load("${SERVER_URL}image/${idnt[count]!!.ima}").into(binding.secondImage)
            binding.SItext.text = idnt[count++]!!.name
            when (round) {
                4 -> {
                    binding.progressing.text = "준결승 " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"
                }
                2 -> {
                    binding.progressing.text = "결승"
                }
                else -> {
                    binding.progressing.text = round.toString() + "강 " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"
                }
            }
        }
    }
}