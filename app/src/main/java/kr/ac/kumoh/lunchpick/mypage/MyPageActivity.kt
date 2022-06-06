package kr.ac.kumoh.lunchpick.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import kr.ac.kumoh.lunchpick.sharedPreference.LocalUser
import kr.ac.kumoh.lunchpick.VolleySingleton
import kr.ac.kumoh.lunchpick.databinding.ActivityMyPageBinding
import org.json.JSONArray
import org.json.JSONObject

class MyPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userInfoRequest()

        binding.btnUpdatePw.setOnClickListener {
            val curPw = binding.editCurPw.text.toString()
            val newPw = binding.editNewPw.text.toString()
            var checkPw = true
            if (!newPw.matches("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,16}\$".toRegex()))
            {
                checkPw = false
                Toast.makeText(this, "비밀번호는 숫자, 문자, 특수문자 중 2가지를 포함(8~16자)하여 구성해주세요.", Toast.LENGTH_SHORT).show()
            }
            if (curPw!=LocalUser.prefs.getString("pw", "")) {
                checkPw = false
                Toast.makeText(this,"현재 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show()
            }
            if (checkPw) {
                updatePwRequest(newPw)
                Toast.makeText(this,"비밀번호가 변경되었습니다.", Toast.LENGTH_SHORT).show()
                binding.editCurPw.setText("")
                binding.editNewPw.setText("")
                LocalUser.prefs.setString("pw", newPw)
            }
        }
    }

    private fun userInfoRequest() {
        val url = "https://csproject-qejmc.run.goorm.io/UserInfo"

        val param = JSONObject()
        param.put("user_ID", LocalUser.prefs.getString("id", ""))
        val params = JSONArray()
        params.put(param)

        val request = JsonArrayRequest(
            Request.Method.POST,
            url,
            params,
            {
                parseUserInfo(it)
                binding.tvUserNameContent.text = LocalUser.prefs.getString("uname", "")
                binding.tvUID.text = LocalUser.prefs.getString("id", "")
            },
            {
                Log.e("volley", "failed")
            }
        )
        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }

    private fun parseUserInfo(items: JSONArray){
        for (i in 0 until items.length()) {
            val item: JSONObject = items.getJSONObject(i)
            LocalUser.prefs.setString("id", item.getString("user_ID"))
            LocalUser.prefs.setString("pw", item.getString("user_PW"))
            LocalUser.prefs.setString("uname", item.getString("user_name"))
            LocalUser.prefs.setString("gender", item.getString("user_gender"))
            LocalUser.prefs.setInt("age", item.getInt("user_age"))
        }
    }

    private fun updatePwRequest(newPw:String){
        val url = "https://csproject-qejmc.run.goorm.io/updatePw"

        val param = JSONObject()
        param.put("user_ID", LocalUser.prefs.getString("id", ""))
        param.put("newPw", newPw)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            param,
            {
                val response = it.getString("result")
                Log.e("volley", response)
            },
            {
                Log.e("volley", "failed")
            }
        )

        VolleySingleton.getInstance(this).addToRequestQueue(request)
    }
}