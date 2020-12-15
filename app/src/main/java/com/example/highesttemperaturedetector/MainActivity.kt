package com.example.highesttemperaturedetector

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.longdo.mjpegviewer.MjpegView

class MainActivity : AppCompatActivity() {
    lateinit var buttonUseLocalhost: Button
    lateinit var buttonUseServer: Button
    lateinit var mjpegView: MjpegView
    lateinit var infoText: TextView

    val SERVER_URL = "http://45.80.181.165:8000"
    val LOCALHOST_URL = "http://10.0.2.2:8000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.buttonUseLocalhost = findViewById(R.id.buttonUseLocalhost)
        this.buttonUseServer = findViewById(R.id.buttonUseServer)
        this.mjpegView = findViewById(R.id.mjpegViewer)
        this.infoText = findViewById(R.id.infoText)

        mjpegView.mode = MjpegView.MODE_BEST_FIT

        this.buttonUseLocalhost.setOnClickListener {
            infoText.text = getString(R.string.connecting_to_localhost)
            mjpegView.setUrl(LOCALHOST_URL)
            infoText.text = getString(R.string.connected_to_localhost)
            mjpegView.startStream()
        }

        this.buttonUseServer.setOnClickListener {
            infoText.text = getString(R.string.connecting_to_server)
            mjpegView.setUrl(SERVER_URL)
            infoText.text = getString(R.string.connected_to_server)
            mjpegView.startStream()
        }
    }
}