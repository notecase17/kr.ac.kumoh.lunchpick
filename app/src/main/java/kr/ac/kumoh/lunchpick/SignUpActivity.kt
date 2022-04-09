package kr.ac.kumoh.lunchpick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.lunchpick.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private var isBlank = false
    private var isPwSame = false

    private lateinit var userGender: String

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gender.setOnCheckedChangeListener { group, checkId ->
            when(checkId) {
                R.id.rbMale -> userGender = "남"
                R.id.rbFemale -> userGender = "여"
            }
        }

        binding.btnSignUp.setOnClickListener {
            val userId = binding.editUserId.text.toString()
            val userPw1 = binding.editUserPw1.text.toString()
            val userPw2 = binding.editUserPw2.text.toString()
            val userName = binding.editName.text.toString()
            val userAge = binding.editAge.text.toString()

            if (userId.isEmpty() || userPw1.isEmpty() || userPw2.isEmpty() || userName.isEmpty() || userAge.isEmpty())
                signUpError("blank")
        }

    }

    private fun signUpError(type: String) {
        when(type) {
            "blank" -> Toast.makeText(this, "모든 항목을 입력해 주세요", Toast.LENGTH_SHORT).show()
        }
    }
}