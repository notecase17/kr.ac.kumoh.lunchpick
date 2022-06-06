package kr.ac.kumoh.lunchpick.SharedPreference
import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context)
{
    private val prefs: SharedPreferences = context.getSharedPreferences("other", 0)

    fun getString(key: String, defValue: String): String
    {
        return prefs.getString(key, defValue).toString()
    }

    fun getBool(key: String, defValue: Boolean): Boolean
    {
        return prefs.getBoolean(key, defValue)
    }

    fun setString(key: String, str: String)
    {
        prefs.edit().putString(key, str).apply()
    }

    fun setBool(key: String, bool: Boolean)
    {
        prefs.edit().putBoolean(key, bool).apply()
    }
}