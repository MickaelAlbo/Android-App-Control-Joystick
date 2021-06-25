package com.example.android_app_remote_control_joystick.ViewModel

import com.example.android_app_remote_control_joystick.Model.Model

class ViewModel {
    private var model: Model = Model();
    fun setElevator(newVal:Float) {
        this.model.setElevatorVal(newVal)
    }
    fun setAileron(newVal:Float) {
        this.model.setAileronVal(newVal)
    }
    fun setThrottle(newVal:Float){
        this.model.setThrottleVal(newVal)
    }
    fun setRudder(newVal:Float){
        this.model.setRudderVal(newVal)
    }
    fun exitApp() {
        model.exitApp()
    }
    fun connectToFg(port: Int, ip: String){
        this.model.connectToFg(ip,port);
    }
    fun disconnectFromFg() {
        model.disconnectFromFg()
    }
}