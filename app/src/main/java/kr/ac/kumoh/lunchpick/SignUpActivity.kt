package kr.ac.kumoh.lunchpick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.lunchpick.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  click customer sign up
        binding.btnCustomer.setOnClickListener {
            val intent = Intent(this, CustomerSignUpActivity::class.java)
            startActivity(intent)
        }

        //  click store sign up
        binding.btnStore.setOnClickListener {
            //  move to store sign up activity
        }
    }
}