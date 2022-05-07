package kr.ac.kumoh.lunchpick

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kr.ac.kumoh.lunchpick.databinding.ActivityGameBinding
import java.io.Serializable

data class food (
    var name: String
) : Serializable

class GameActivity : AppCompatActivity() {
    private lateinit var binding : ActivityGameBinding
    var idnt = Array<food?>(32) {null}
    var count = 0
    var c_count = 0
    var round = 0
    var n_round = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fd1 = food("초밥")
        val fd2 = food("떡볶이")
        val fd3 = food("라면")
        val fd4 = food("김치찌개")
        val fd5 = food("파스타")
        val fd6 = food("치킨")
        val fd7 = food("짜장면")
        val fd8 = food("피자")
        val fd = arrayOf<food>(fd1,fd2,fd3,fd4,fd5,fd6,fd7,fd8)
        c_count = fd.size

        for(i in fd.indices){
            idnt[i]=fd[i]
        }

        round = fd.size

        binding.showImage.text = idnt[count++]!!.name
        binding.showImage2.text = idnt[count++]!!.name
        binding.progressing.text = round.toString() + "강 " + "( "+n_round.toString()+" / "+(round/2).toString()+" )"

        binding.showImage.setOnClickListener {
            Toast.makeText(this, binding.showImage.text.toString(), Toast.LENGTH_SHORT).show()
            val fd = food(binding.showImage.text.toString())
            idnt[c_count++]=fd
            click()
        }
        binding.showImage2.setOnClickListener {
            Toast.makeText(this, binding.showImage.text.toString(), Toast.LENGTH_SHORT).show()
            val fd = food(binding.showImage.text.toString())
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
            binding.showImage.text = idnt[count++]!!.name
            binding.showImage2.text = idnt[count++]!!.name
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