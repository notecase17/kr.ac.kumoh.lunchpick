package kr.ac.kumoh.lunchpick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import kr.ac.kumoh.lunchpick.SharedPreference.LocalUser
import kr.ac.kumoh.lunchpick.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userid : String
    private lateinit var userpw : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LocalUser.prefs.setBool("logon", false)

        //  click login btn
        binding.btnLogIn.setOnClickListener {

            userid=binding.editUserId.text.toString()
            userpw=binding.editUserPw.text.toString()

            if (userid.isEmpty())
                Toast.makeText(this, "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show()
            if (userid.isNotEmpty() && userpw.isEmpty())
                Toast.makeText(this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
            if (userid.isNotEmpty() && userpw.isNotEmpty()){
                //  id, pw check
                loginRequest(userid, userpw)
            }
        }

        //  click sign up button: go to sign up activity
        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loginRequest(id: String, pw: String) {
        val url = "https://csproject-qejmc.run.goorm.io/login"

        val param = JSONObject()
        param.put("user_ID", id)
        param.put("user_PW", pw)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            param,
            {
                val response = it.getString("result")
                Log.e("volley", response)

                //  response check
                Log.e("volley", response)
                if (response=="idfalse"){
                    Toast.makeText(this, "일치하는 아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
                if (response=="pwfalse"){
                    Toast.makeText(this, "잘못된 비밀번호 입니다.", Toast.LENGTH_SHORT).show()
                }
                if (response=="success"){
                    Toast.makeText(this, "환영합니다", Toast.LENGTH_SHORT).show()

                    //  로그온 상태, id, pw 저장
                    LocalUser.prefs.setBool("logon", true)
                    LocalUser.prefs.setString("id", id)
                    LocalUser.prefs.setString("pw", pw)

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            },
            {
                Log.e("volley", "failed")
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }
}