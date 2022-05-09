package kr.ac.kumoh.lunchpick.SharedPreference

import android.app.Application

class LocalUser : Application()
{
    companion object
    {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate()
    {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}