package kr.ac.kumoh.lunchpick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kr.ac.kumoh.lunchpick.databinding.ActivityCustomerSignUpBinding

class CustomerSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCustomerSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCustomerSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}