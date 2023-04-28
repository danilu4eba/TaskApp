package com.example.taskapp.data.local

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class Pref(private val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(TASK_PREF_KEY, MODE_PRIVATE)


    fun isUserSeen(): Boolean {
        return pref.getBoolean(USER_SEEN_KEY, false)
    }

    fun saveUserSeen() {
        pref.edit().putBoolean(USER_SEEN_KEY, true).apply()
    }

    fun saveName(name:String) {
       pref.edit().putString(USER_WROTE,name).apply()
    }
    fun getName():String{
        return pref.getString(USER_WROTE,"").toString()
    }
    fun saveImg(img: String){
        pref.edit().putString(KEY_IMG, img).apply()
    }

    fun getImg(): String? {
        return pref.getString(KEY_IMG,"")
    }


    companion object {
        const val TASK_PREF_KEY = "Key"
        const val USER_SEEN_KEY = "Key2"
        const val USER_WROTE = "key3"
        const val KEY_IMG = "key4"
    }

}

