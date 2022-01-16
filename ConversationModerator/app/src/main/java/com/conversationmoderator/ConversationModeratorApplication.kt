package com.conversationmoderator

import android.app.Application
import android.content.IntentFilter
import com.exertframework.UserPreferencesManager.UserStateManager
import com.exertframework.Utility.ConnectivityReceiver
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump

class ConversationModeratorApplication: Application()  {
    companion object {
        lateinit var mInstance: ConversationModeratorApplication
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
//        RxFileChooser.register(this)
        var intentFilter =  IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        this.registerReceiver( ConnectivityReceiver(), intentFilter);
        UserStateManager.instance.setContext(this)
//        ViewPump.init(
//            ViewPump.builder()
//                .addInterceptor(
//                    CalligraphyInterceptor(
//                        CalligraphyConfig.Builder()
//                            .setDefaultFontPath("fonts/Flexo-Regular.ttf")
//                            .setFontAttrId(R.attr.fontPath)
//                            .build()
//                    )
//                )
//                .build()
//        )

    }

    fun setConnectivityListener(listener: ConnectivityReceiver.ConnectivityReceiverListener) {
        ConnectivityReceiver.connectivityReceiverListener = listener
    }
}