package com.exertframework.RestAPIClient

import com.exertframework.UserPreferencesManager.UserStateManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthenticationInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val request = original.newBuilder()
            .header("Accept", "application/json")
            .header("Authorization", "Bearer " + UserStateManager.instance.sSPManager.getSaveData("token"))//UserStateManager.instance.sSPManager.getSaveData("token")
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}