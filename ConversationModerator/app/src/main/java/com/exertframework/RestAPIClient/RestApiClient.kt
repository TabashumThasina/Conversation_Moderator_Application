package com.exertframework.RestAPIClient

import android.content.Context
import android.util.Log
import com.exertframework.UserPreferencesManager.UserStateManager
import com.exertframework.Utility.Utility
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import android.R.string
import android.content.DialogInterface
import android.view.View
import com.exertframework.Utility.AlertUtility
import com.exertframework.Utility.ConnectivityReceiver
import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import com.qf.giftapp.Interfaces.AlertDialogInterface
//import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import okhttp3.ResponseBody
import retrofit2.*
import java.io.IOException


object RestApiClient {

    private var REST_CLIENT: API? = null
    private var httpClient: OkHttpClient.Builder? = null
    private lateinit var retrofit: Retrofit;
    private val token = ""

    init {
        setupRestClient()
    }

    fun get(): API? {
        if (REST_CLIENT == null) {
            setupRestClient()
        }
        return REST_CLIENT
    }

    private fun setupRestClient() {
        if (REST_CLIENT == null) {
            val gson = GsonBuilder()
                .setLenient()
                .create()


            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(0, TimeUnit.MINUTES)
                .addInterceptor(AuthenticationInterceptor())
//                .addInterceptor(logging)

            this.retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient!!.build())
                .build()
            REST_CLIENT = this.retrofit.create(API::class.java)

        }
    }

    fun request(call: Call<JsonElement>, showHud: Boolean?, context: Context, listener: HTTPServiceManagerListener) {
        if (!ConnectivityReceiver.isConnected()) {
//            AlertUtility.createAlertWithRetryandCancel(
//                context,
//                "Alert",
//                "No Internet Connection",
//                object : AlertDialogInterface {
//                    override fun ok(dialog: DialogInterface, data: Any) {
//                        super.ok(dialog, data)
//                        request(call, showHud, context, listener)
//                        dialog.dismiss()
//                    }
//
//                    override fun cancel(dialog: DialogInterface, data: Any) {
//                        super.cancel(dialog, data)
//                        dialog.dismiss()
//                    }
//                })
            return
        }
        Utility.showSpinnerProgressDialog(context, showHud!!)
        call.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.code() == 200) {
                    listener.onResponse(response.body()!!.asJsonObject)
                } else {
                    var errorConverter: Converter<ResponseBody, APIError> =
                        retrofit.responseBodyConverter(APIError::class.java, arrayOfNulls(0));
                    try {
                        val error = errorConverter.convert(response.errorBody())
                        error?.message?.let { Log.i("request-onResponse", it) };
                        Utility.hideSpinnerProgressDialog(showHud)
                        listener.onFailure(error?.message!!)
                        return
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    listener.onFailure("request-unknown error")
                }
                Utility.hideSpinnerProgressDialog(showHud)
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i("request-onFailure", t.toString());
                listener.onFailure(t.message.toString())
                Utility.hideSpinnerProgressDialog(showHud)
            }
        })
    } //F.E.

    fun requestLoginLaravel(
        call: Call<JsonElement>,
        showHud: Boolean?,
        context: Context,
        listener: HTTPServiceManagerListener
    ) {
        if (!ConnectivityReceiver.isConnected()) {
//            AlertUtility.createAlertWithRetryandCancel(
//                context,
//                "Alert",
//                "No Internet Connection",
//                object : AlertDialogInterface {
//                    override fun ok(dialog: DialogInterface, data: Any) {
//                        super.ok(dialog, data)
//                        requestLoginLaravel(call, showHud, context, listener)
//                        dialog.dismiss()
//                    }
//
//                    override fun cancel(dialog: DialogInterface, data: Any) {
//                        super.cancel(dialog, data)
//                        dialog.dismiss()
//                    }
//                })
            return
        }
        Utility.showSpinnerProgressDialog(context, showHud!!)
        call.enqueue(object : Callback<JsonElement> {
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                if (response.code() == 200) {
                    UserStateManager.instance.sSPManager.saveStringvalue(
                        "token",
                        response.headers().get("token")!!,
                        context
                    )
                    listener.onResponse(response.body()!!.asJsonObject)

                }else{
                    Log.i("requestLoginLaravel" , response.errorBody().toString());

                    var errorConverter : Converter<ResponseBody, APIError> =
                        retrofit.responseBodyConverter(APIError::class.java, arrayOfNulls(0));
                    try {
                        val error = errorConverter.convert(response.errorBody())
                        error?.message?.let { Log.i("response" , it) };
                        listener.onFailure(error?.message!!)
                        Utility.hideSpinnerProgressDialog(showHud)
                        return

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    listener.onFailure("unknown error")
                }
                Utility.hideSpinnerProgressDialog(showHud)
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.i("requestLoginLaravel" , t.message.toString());
                listener.onFailure(t.message.toString())
                Utility.hideSpinnerProgressDialog(showHud)
            }
        })
    } //F.E.

    // This method  converts String to RequestBody
    fun toRequestBodyString(value: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), value)
    }

    fun toRequestBodyFile(value: File): RequestBody {
        return RequestBody.create("image/*".toMediaTypeOrNull(), value)
    }
    fun toRequestBodyFilePDF(value: File): RequestBody {
        return RequestBody.create("application/pdf".toMediaTypeOrNull(), value)
    }
}