package kr.ac.kumoh.lunchpick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.kumoh.lunchpick.SharedPreference.LocalUser
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

            userid=binding.editUserId.text.toString()
            userpw=binding.editUserPw.text.toString()

            if (userid.isEmpty())
                Toast.makeText(this, "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show()
            if (userid.isNotEmpty() && userpw.isEmpty())
                Toast.makeText(this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
            if (userid.isNotEmpty() && userpw.isNotEmpty()){

                //  check id, pw.
                //  입력된 id, pw를 db에 쿼리로 검색
                //  id, pw가 일치한다면 다음 액티비티로 이동

                if (loginCheck(userid, userpw)) {
                    val intent = Intent(this, GameActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        //  click sign up button: go to sign up activity
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loginCheck(id: String, pw: String) : Boolean {
        if (id != LocalUser.prefs.getString("id", "")) {
            Toast.makeText(this, "일치하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        else if (pw != LocalUser.prefs.getString("pw", "")) {
            Toast.makeText(this, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        else {
            Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
            return true
        }
    }
}