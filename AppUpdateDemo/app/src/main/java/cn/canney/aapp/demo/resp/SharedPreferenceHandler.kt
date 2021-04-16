package cn.canney.aapp.demo.resp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferenceHandler(private var context: Context){

    val spref = context.getSharedPreferences("cn.canney.aapp", MODE_PRIVATE)

    fun getString(key: String): String? {
        return spref.getString(key, null)
    }

    fun putString(key: String, value: String?){
        spref.edit().putString(key, value).commit()
    }

}