package com.example.highesttemperaturedetector

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.longdo.mjpegviewer.MjpegView
import java.lang.NullPointerException
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var buttonUseLocalhost: Button
    lateinit var buttonUseServer1: Button
    lateinit var buttonUseServer2: Button
    lateinit var mjpegView: MjpegView
    lateinit var infoText: TextView
    lateinit var portEdit: EditText
    lateinit var hostEdit: EditText

    private val serverHost1 = "http://45.80.181.165"
    private val serverPort1 = 5001

    private val serverHost2 = "http://45.80.181.165"
    private val serverPort2 = 5002

    private val localHost = "http://192.168.1.10"
    private val localPort = 5000

    var host: String? = null
    var port: Int? = null


    private fun setMjpegViewUrl(host: String, port: Int) {
        this.host = host
        this.port = port
        this.hostEdit.setText(this.host)
        this.portEdit.setText(this.port.toString())
        refreshMjpegViewer()
    }

    private fun refreshMjpegViewer() {
        try {
            mjpegView.stopStream()
        } catch (e: NullPointerException) {
            // Do nothing
        }

        this.mjpegView.setUrl("${this.host}:${this.port}")
        mjpegView.startStream()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.buttonUseLocalhost = findViewById(R.id.buttonUseLocalhost)
        this.buttonUseServer1 = findViewById(R.id.buttonUseServer1)
        this.buttonUseServer2 = findViewById(R.id.buttonUseServer2)
        this.hostEdit = findViewById(R.id.hostEdit)
        this.portEdit = findViewById(R.id.portEdit)
        this.mjpegView = findViewById(R.id.mjpegViewer)
        this.infoText = findViewById(R.id.infoText)

        mjpegView.mode = MjpegView.MODE_STRETCH

        this.buttonUseLocalhost.setOnClickListener {
            setMjpegViewUrl(localHost, localPort)
        }

        this.buttonUseServer1.setOnClickListener {
            setMjpegViewUrl(serverHost1, serverPort1)
        }

        this.buttonUseServer2.setOnClickListener {
            setMjpegViewUrl(serverHost2, serverPort2)
        }

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                host?.let { host ->
                    port?.let { port ->
                        if (host.startsWith("http://") || host.startsWith("https://")) {
                            refreshMjpegViewer()
                        }
                    }
                }


                mainHandler.postDelayed(this, 3000)
            }
        })
    }
}