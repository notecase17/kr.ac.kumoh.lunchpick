package kr.ac.kumoh.lunchpick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.lunchpick.mypage.MyPageActivity
import kr.ac.kumoh.lunchpick.sharedPreference.LocalUser
import kr.ac.kumoh.lunchpick.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSettings.bringToFront()
        binding.ivSettings.bringToFront()
        binding.tvLogOut.bringToFront()

        //  move to data setting
        binding.tvSettings.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }

        //  start menu world cup
        binding.ivFoodCup.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("cupType", "menu")
            startActivity(intent)
        }

        //  start restaurant world cup
        binding.ivResCup.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("cupType", "restaurant")
            startActivity(intent)
        }

        //  logout
        binding.tvLogOut.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

            //  로그온 상태, id, pw 초기화
            LocalUser.prefs.setBool("logon", false)
            LocalUser.prefs.setString("id", "")
            LocalUser.prefs.setString("pw", "")

            finish()
        }
    }
}