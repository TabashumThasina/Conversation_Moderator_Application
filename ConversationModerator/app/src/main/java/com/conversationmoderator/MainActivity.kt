package com.conversationmoderator

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.RadialGradient
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.aghajari.waveanimation.AXLineWaveView
import com.aghajari.waveanimation.AXWaveView
import java.util.*
import kotlin.math.log10
import android.graphics.Shader
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat

import com.aghajari.waveanimation.AXWeavingState
import com.chibde.visualizer.LineBarVisualizer
import com.conversationmoderator.Models.Data
import com.exertframework.RestAPIClient.HTTPServiceManagerListener
import com.exertframework.RestAPIClient.RestApiClient
import com.exertframework.Utility.AlertUtility
import com.exertframework.Utility.FileUtils
import com.exertframework.Utility.Utility
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity()   {

    private var debug_mode = true

    companion object {
        private var tag = "MainActivity"
        private var defaultFileName = "sound.wav"
    }
    private var recorderStatus = false
    private var audioFileURL = ""
    private var subsDurationMillis = 500
    private var _meteringEnabled = false
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var recorderRunnable: Runnable? = null
    private var mTask: TimerTask? = null
    private var mTimer: Timer? = null
    private var pausedRecordTime = 0L
    private var totalPausedRecordTime = 0L
    var recordHandler: Handler? = Handler(Looper.getMainLooper())
    private lateinit var waveView : AXWaveView
    private lateinit var txtClock : TextView
    private lateinit var img : AppCompatImageView
    fun getName(): String {
        return tag
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Recorder"
        txtClock = findViewById<TextView>(R.id.txtClock)
        waveView = findViewById<AXWaveView>(R.id.wave)
        img = findViewById<AppCompatImageView>(R.id.img)
        waveView.isCircleEnabled = true
        AXWaveView.MAX_AMPLITUDE
        waveView.visibility = View.INVISIBLE
        img.visibility = View.VISIBLE
//        val intent = Intent( this@MainActivity, AnalyticsActivity::class.java)
//        startActivity(intent)
//        waveView.addState(
//            1, AXWeavingState.create(
//                1,
//                RadialGradient(
//                    200f,
//                    200f,
//                    200f,
//                    intArrayOf(-0xd43101, -0xf6891d),
//                    null,
//                    Shader.TileMode.CLAMP
//                )
//            )
//        )
//
//        waveView.addState(2, object : AXWeavingState(2) {
//            override fun updateTargets() {
//                targetX = 0.2f + 0.1f * random.nextInt(100) / 100f
//                targetY = 0.7f + 0.1f * random.nextInt(100) / 100f
//            }
//
//            override fun createShader(): Shader {
//                return RadialGradient(
//                    200f,
//                    200f,
//                    200f,
//                    intArrayOf(-0xed4ade, -0xff293f),
//                    null,
//                    Shader.TileMode.CLAMP
//                )
//            }
//        })
//
//        waveView.setState(2)
    }

    fun startRecorder(meteringEnabled: Boolean) {
        recorderStatus = true
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                (ActivityCompat.checkSelfPermission(this.applicationContext, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED ||
                        ActivityCompat.checkSelfPermission(this.applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                ActivityCompat.requestPermissions((this)!!, arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
                return
            }
        } catch (ne: NullPointerException) {
            Log.w(tag, ne.toString())
            return
        }

        audioFileURL = externalCacheDir?.absolutePath + "/recording.wav"
        Log.i(tag,audioFileURL)
        _meteringEnabled = meteringEnabled

        if (mediaRecorder == null) {
            mediaRecorder = MediaRecorder()
        }

        mediaRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder!!.setAudioEncodingBitRate(128000)
        mediaRecorder!!.setAudioSamplingRate(48000)
        mediaRecorder!!.setOutputFile(audioFileURL)

        try {

            mediaRecorder!!.prepare()
            totalPausedRecordTime = 0L
            mediaRecorder!!.start()
            val systemTime = SystemClock.elapsedRealtime()
            recorderRunnable = object : Runnable {
                override fun run() {
                    val time = SystemClock.elapsedRealtime() - systemTime - totalPausedRecordTime
                    txtClock.text = changeTime(time)
                    Log.i(tag, time.toString())
                    waveView.amplitude = mediaRecorder!!.maxAmplitude.toFloat()
//                    val obj = Arguments.createMap()
//                    obj.putDouble("currentPosition", time.toDouble())
                    if (_meteringEnabled) {
                        var maxAmplitude = 0
                        if (mediaRecorder != null) {
                            maxAmplitude = mediaRecorder!!.maxAmplitude
                        }
                        var dB = -160.0
                        val maxAudioSize = 32767.0
                        if (maxAmplitude > 0) {
                            dB = 20 * log10(maxAmplitude / maxAudioSize)
                        }
//                        obj.putInt("currentMetering", dB.toInt())
                    }
                    recordHandler!!.postDelayed(this, subsDurationMillis.toLong())
                }
            }
            (recorderRunnable as Runnable).run()
        } catch (e: Exception) {
            Log.e(tag, "Exception: ", e)
        }
    }
    fun stopRecorder() {
        if (recordHandler != null) {
            recorderRunnable?.let { recordHandler!!.removeCallbacks(it) }
        }

        try {
            mediaRecorder!!.stop()
        } catch (stopException: RuntimeException) {
            stopException.message?.let { Log.d(tag,"" + it) }
        }

        mediaRecorder!!.release()
        mediaRecorder = null
    }
    fun btnStart(view: android.view.View) {
        view as (Button)

        if(this.recorderStatus){

            waveView.visibility = View.INVISIBLE
            img.visibility = View.VISIBLE
            this.stopRecorder()
            view.text = "Start"
            this.recorderStatus = false
            if(debug_mode){
                val input = applicationContext.assets.open("commercial_mono.wav")
                val file = File(cacheDir, "cacheFileAppeal.wav")

                try {


                    FileOutputStream(file).use { output ->
                        val buffer = ByteArray(4 * 1024) // or other buffer size
                        var read: Int = 0
                        while (input.read(buffer).also { read = it } != -1) {
                            output.write(buffer, 0, read)
                        }
                        output.flush()
                    }
                    callApiDiariazition(file,false)

                } finally {
                    input.close()
                }
            }else{
                callApiDiariazition(File(audioFileURL),false)
            }
        }else{
            waveView.visibility = View.VISIBLE
            img.visibility = View.INVISIBLE
            this.startRecorder(false)
            view.text = "Analyse"
            this.recorderStatus = true



        }
    }


    fun changeTime(millis: Long): String {
//        val millis: Long = 3600000
        var hms = java.lang.String.format(
            "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(
                    millis
                )
            ),
            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis
                )
            )
        )
        return hms
    }

    private fun callApiDiariazition(file: File, isPDF : Boolean){
        Log.i("absolutePath",file.absolutePath)
        val dictionary = HashMap<String, RequestBody>()
        //Utility.imageCompression(file, 10)
        dictionary["file\"; filename=\"" + file.name + "\""] = file.asRequestBody("audio/x-wav".toMediaTypeOrNull())// RestApiClient.toRequestBodyFilePDF(file)

        dictionary["encoding"] = RestApiClient.toRequestBodyString("WAV")
        RestApiClient.request(
            RestApiClient.get()!!.diarization(dictionary),
            true,
            this,
            object : HTTPServiceManagerListener {
                override fun onResponse(response: JsonObject) {
                    val u = Utility.getObjectFromJsonObject<Data>(
                        response.get("data"),
                        Data::class.java
                    ) as Data
                    toastTest(response.toString())
                    val intent = Intent( this@MainActivity, AnalyticsActivity::class.java)
                    Log.i("callApiDiariazition",response.toString())
                    intent.putExtra("data", u)
                    startActivity(intent)
//                    Log.i("onResponse",response.toString())
//                    AlertUtility.createAlertWithOk(
//                        this@GenerateReceivingNoteActivity,
//                        resources.getString(R.string.title),
//                        response.get("message").asString,
//                        object : AlertDialogInterface {})
//
//                    var r = Receipt()
//                    r.id = response.getAsJsonArray("receipts").asJsonArray.get(response.getAsJsonArray("receipts").asJsonArray.size()-1).asJsonObject.get("id").asInt
//                    r.file = response.getAsJsonArray("receipts").asJsonArray.get(response.getAsJsonArray("receipts").asJsonArray.size()-1).asJsonObject.get("file").asString
//                    data.receipts!!.add(r)
//                    gvAdapter.notifyDataSetChanged()
                }
                override fun onFailure(error: String) {
                    Log.i("onFailure", error)
                }
            })

    }
    fun toastTest (text: String) {
        val toast = Toast.makeText(this,text,Toast.LENGTH_LONG)
        toast.show()
    }
}
