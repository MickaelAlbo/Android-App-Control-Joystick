package com.example.android_app_remote_control_joystick.View

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.android_app_remote_control_joystick.R
import com.example.android_app_remote_control_joystick.ViewModel.ViewModel


class MainActivity : AppCompatActivity() {
    private var viewModel: ViewModel = ViewModel()
    private lateinit var connectButton: Button
    private lateinit var disconnectButton: Button
    private lateinit var rudderBar: SeekBar
    private lateinit var throttleBar: SeekBar
    private lateinit var joystick: Joystick

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        connectButton = findViewById(R.id.connect_button)
        disconnectButton = findViewById(R.id.disconnect_button)
//    val exitButton: Button = findViewById(R.id.Exit)
        rudderBar = findViewById(R.id.rudder_seek_bar)
        throttleBar = findViewById(R.id.throttle_seek_bar)
        joystick = findViewById(R.id.joystick_iframe)
        listenToRudderSeekBar()
        listenToThrottleSeekBar()
        joystick.onJoystickChange = { aileron: Float, elevator: Float ->
            viewModel.setAileron(aileron)
            viewModel.setElevator(elevator)
        }
        connectButton.setOnClickListener { connectToFlightGear() }
        disconnectButton.setOnClickListener { disconnect() }
//    exitButton.setOnClickListener { exit() }
    }

    private fun listenToRudderSeekBar() {
        this.rudderBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = rudderBar.progress / 100F;
                viewModel.setRudder(value)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun listenToThrottleSeekBar() {
        this.throttleBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = throttleBar.progress / 100F;
                viewModel.setThrottle(value)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

private fun connectToFlightGear() {
    try {
        val ip = findViewById<EditText>(R.id.ip_field).text.toString()
        val port = findViewById<EditText>(R.id.port_field).text.toString().toInt()
        viewModel.connectToFg(port, ip)
        connectButton.text = "Connected!"
        connectButton.isEnabled = false
    } catch (e: Exception) {
        e.printStackTrace()
        val text = "Connection failed \nCheck if your IP and Port are valid"
        val duration = Toast.LENGTH_LONG

        val toast = Toast.makeText(applicationContext, text, duration).show()
    }
}

private fun disconnect() {
    try {
        viewModel.disconnectFromFg()
        connectButton.text = "Connect!"
        connectButton.isEnabled = true
        val toast =
            Toast.makeText(applicationContext, "DisConnection succeeded!", Toast.LENGTH_SHORT)
                .show()
    } catch (e: Exception) {
        e.printStackTrace()
        val text = "Disconnection failed \nTry again or check if connection was made"
        val duration = Toast.LENGTH_LONG
        val toast = Toast.makeText(applicationContext, text, duration).show()
    }
}

private fun exit() {
    viewModel.exit()
}
}