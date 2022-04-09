package kr.ac.kumoh.lunchpick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.lunchpick.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userid : String
    private lateinit var userpw : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  click login btn
        binding.btnLogIn.setOnClickListener {

            userid=binding.editUserId.text.toString().trim()
            userpw=binding.editUserPw.text.toString().trim()

            if (userid.isEmpty())
                Toast.makeText(this, "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show()
            if (userid.isNotEmpty() && userpw.isEmpty())
                Toast.makeText(this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
            if (userid.isNotEmpty() && userpw.isNotEmpty()){

                //  check id, pw.
                //  입력된 id, pw를 db에 쿼리로 검색
                //  id, pw가 일치한다면 다음 액티비티로 이동

                Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            }
        }

        //  click sign up button: go to sign up activity
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}