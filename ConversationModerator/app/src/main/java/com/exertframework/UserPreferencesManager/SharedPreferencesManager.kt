package com.exertframework.UserPreferencesManager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
//import com.qf.giftapp.Models.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesManager(paramContext: Context) {

    internal var StateObject = UserStateManager.instance
    private var prefsEditor: SharedPreferences.Editor? = null
    private var sharedPreferences: SharedPreferences? = null
    private val sharedPreferencesName = "GiftApp"
    //ModelSpecific var
//    private var user: User? = null

    init {
        sharedPreferences = paramContext.getSharedPreferences(this.sharedPreferencesName, 0)
        prefsEditor = this.sharedPreferences!!.edit()
        prefsEditor!!.apply()
    }
    //Save and Get Boolean Data
    fun saveBooleanData(key: String, value: Boolean) {
        prefsEditor!!.putBoolean(key, value)
        prefsEditor!!.commit()
    }
    fun getbooleanData(key: String, context: Context): Boolean {
        return sharedPreferences!!.getBoolean(key, false)
    }
    //Generic
    fun saveData(key: String, value: Boolean, context: Context) {
        prefsEditor!!.putBoolean(key, value)
        prefsEditor!!.commit()
    }
    fun saveStringvalue(key: String, value: String, context: Context) {
        prefsEditor!!.putString(key, value)
        prefsEditor!!.commit()
    }

    fun getSaveData(key: String): String? {
        return sharedPreferences!!.getString(key, "0")
    }

//    //ModelSpecific
//    fun getUser(): User? {
//        if (user == null) {
//            val gson = Gson()
//            val jsonString = sharedPreferences!!.getString("User", "")
//            Log.i("getUser",jsonString)
//            if (jsonString == "") {
//                return null
//            } else {
//                user = gson.fromJson<User>(jsonString, object : TypeToken<User>() {
//
//                }.type)
//                return user
//            }
//        } else {
//            return user
//        }
//    }
//
//    fun setUser(user: User) {
//        this.user = user
//        val localGson = Gson()
//        val str = localGson.toJson(user)
//        prefsEditor!!.putString("User", str)
//        prefsEditor!!.apply()
//    }
    fun clearSharedPreferences(context: Context) {
//        user = null
        sharedPreferences = context.getSharedPreferences(this.sharedPreferencesName, 0)
        prefsEditor = this.sharedPreferences!!.edit()
        prefsEditor!!.clear()
        prefsEditor!!.apply()
    }

    companion object {
        val TAG = SharedPreferencesManager::class.java.simpleName
    }
}