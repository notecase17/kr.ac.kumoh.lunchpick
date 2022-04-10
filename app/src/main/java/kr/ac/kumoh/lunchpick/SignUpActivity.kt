package kr.ac.kumoh.lunchpick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kr.ac.kumoh.lunchpick.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private var isBlank = false
    private var isPwSame = false
    // 아래 item_gender 불러오는 것에서 nullPointerException 에러 발생
    //var item_gender = resources.getStringArray(R.array.gender)
    //var spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, item_gender)

    private lateinit var userGender: String

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /* Spinner 사용 시 코드
        binding.spGender.adapter = spinnerAdapter
        binding.spGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long)
            {
                when (position) {
                    0 -> {
                        userGender = "남"
                    }
                    1 -> {
                        userGender = "여"
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        */

        /* 라디오 버튼 사용 시 코드
        binding.gender.setOnCheckedChangeListener { group, checkId ->
            when(checkId) {
                R.id.rbMale -> userGender = "남"
                R.id.rbFemale -> userGender = "여"
            }
        }
        */

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