package com.exertframework.UserPreferencesManager

import android.content.Context


class UserStateManager {
    val TAG = UserStateManager::class.java.simpleName

    lateinit var mContext: Context
    lateinit var sSPManager: SharedPreferencesManager

    fun setContext(paramContext: Context) {
        mContext = paramContext
        val localSharedPreferencesManager = SharedPreferencesManager(mContext)
        sSPManager = localSharedPreferencesManager
    }

    companion object {

        val instance: UserStateManager = UserStateManager()
//            get() {
//                if (_instance == null) {
//                    val localUserStateManager = UserStateManager()
//                    _instance = localUserStateManager
//                }
//                return _instance
//            }
    }
}
