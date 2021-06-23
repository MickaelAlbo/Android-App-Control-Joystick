package com.example.android_app_remote_control_joystick.Model

import android.widget.SeekBar
import java.io.IOException
import java.io.OutputStream
import java.net.Socket
import kotlin.system.exitProcess

class Model {

    private var isConnected: Boolean = false
    private lateinit var socket: Socket
    private lateinit var output: OutputStream
    private lateinit var rudder: SeekBar
    private lateinit var throttle: SeekBar

    fun connectToFg(ip: String, port:Int){
        val connectThread = Thread(Runnable {
            this.socket = Socket(ip, port)
            this.output = this.socket.getOutputStream()
            this.isConnected = true
        })
        connectThread.start()
        connectThread.join()
    }

    fun sendDataToFg(data: String) {
        val data_thread = Thread(Runnable {
            try {
                output.write(data.toByteArray(Charsets.UTF_8))
                output.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        data_thread.start()
        data_thread.join()
    }


    fun disconnectFromFg() {
        this.output.close()
        this.socket.close()
        this.isConnected = false
    }

    fun exit() {
        exitProcess(-1)
    }

    fun setElevatorVal(new_val: Float){
        if(this.isConnected){
            var data = "set /controls/flight/elevator $new_val \r\n"
            this.sendDataToFg(data);
        }
    }
    fun setAileronVal(new_val: Float){
        if(this.isConnected){
            var data = "set /controls/flight/aileron $new_val \r\n"
            this.sendDataToFg(data);
        }
    }
    fun setRudderVal(new_val: Float){
        if(this.isConnected){
            var data = "set /controls/flight/rudder $new_val \r\n"
            this.sendDataToFg(data);
        }
    }
    fun setThrottleVal(new_val: Float){
        if(this.isConnected){
            var data = "set /controls/engines/current-engine/throttle $new_val \r\n"
            this.sendDataToFg(data);
        }
    }

}