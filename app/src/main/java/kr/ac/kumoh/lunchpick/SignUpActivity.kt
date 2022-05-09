package kr.ac.kumoh.lunchpick

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.lunchpick.databinding.ActivitySignUpBinding
import org.json.JSONException
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    private var isBlank = false
    private var isPwSame = false

    private lateinit var userGender: String

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spGender.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.gender))
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

        binding.btnSignUp.setOnClickListener {
            val userId = binding.editUserId.text.toString()
            val userPw1 = binding.editUserPw1.text.toString()
            val userPw2 = binding.editUserPw2.text.toString()
            val userName = binding.editName.text.toString()
            val userAge = binding.editAge.text.toString()

            if (signUpCheck(userId, userPw1, userPw2, userName, userAge)) {

                //signupVolley(this, url, userId, userPw1, userName, userAge, userGender)
                Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun signUpCheck(id: String, pw1: String, pw2: String, name: String, age: String): Boolean {
        if (id.isEmpty() || pw1.isEmpty() || pw2.isEmpty() || name.isEmpty() || age.isEmpty()){
            Toast.makeText(this, "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!pw1.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,16}\$".toRegex()))
        {
            Toast.makeText(this, "비밀번호는 숫자, 문자, 특수문자 중 2가지를 포함(8~16자)하여 구성해주세요.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (pw1!=pw2) {
            Toast.makeText(this,"비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!name.matches("^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,10}\$".toRegex())) {
            Toast.makeText(this, "닉네임은 한글, 영어, 숫자만 사용 가능 (2~10자)", Toast.LENGTH_SHORT).show()
            return false
        }
        if (age.toInt()<=0) {
            Toast.makeText(this, "올바르지 않은 나이입니다.", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun signupVolley(context: Context, url: String, id: String, pw: String, name: String, age: String, gender: String) {
        val requestQueue = Volley.newRequestQueue(context)

        val request: StringRequest = object: StringRequest(Method.POST, url,
        Response.Listener { response ->
            showJSONList(response)
        },
        Response.ErrorListener { error ->
            Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show()
        }) {
            override fun getParams(): MutableMap<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["userid"] = id
                params["userpw"] = pw
                params["username"] = name
                params["userage"] = age
                params["usergender"] = gender

                return params
            }
        }
        requestQueue.add(request)
    }

    fun showJSONList(response: String) {
        try {
            Log.e("TAG",response)
            val jsonObject = JSONObject(response)
            jsonObject.let {
                if(it.getString("userinfo") == null) {
                    showAlert(it.getString("result").toString(), it.getString("message").toString())
                } else {
                    if(it.getString("result").toString().equals("success")){
                        val jsonInfo = JSONObject(it.getString("userinfo").toString())
                        jsonInfo.let{
                            // Preference 에 대한 정보 기록은 생략한다.
                            startActivity(Intent(this,MainActivity::class.java))
                        }
                    }
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    companion object {

        var context: Context? = null

        fun showAlert(title: String, message: String) {
            val builder = context?.let {
                AlertDialog.Builder(it)
                    .setTitle(title)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("OK") { dialog, id -> dialog.dismiss() }
            }
            val alert = builder!!.create()
            alert.show()
        }
    }
}