package com.example.android_app_remote_control_joystick.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.abs
import kotlin.math.min
class Joystick @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var innerCircleCenter: PointF = PointF()
    private var innerCircleRadius: Float = 0f
    private var aileron: Float = 0f
    private var elevator: Float = 0f
    private val outerCircle = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    private val innerCircle = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    private val shadowCircle = Paint().apply {
        color = Color.parseColor("#187574");
        style = Paint.Style.FILL_AND_STROKE
        isAntiAlias = true
    }
    lateinit var onJoystickChange: (Float, Float) -> Unit
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
// make sure actual code handles padding well.
        innerCircleRadius = 0.3f* min(width, height).toFloat()
        innerCircleCenter = PointF(width/2.0f, height/2.0f)

    }
    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(width / 2.0f, height / 2.0f, 320F, outerCircle)
        canvas.drawCircle(width / 2.0f, height / 2.0f, 180F, shadowCircle)
        canvas.drawCircle(innerCircleCenter.x, innerCircleCenter.y, 90F, innerCircle)

    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event?.action) {
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
      }
        return true
    }
    private fun touchMove(x: Float, y: Float){
        if(x <= width/2f +90 && x>= width/2f -90 &&
            y <= height/2f +90 && y>= height/2f -90) {
            innerCircleCenter.x = x
            innerCircleCenter.y = y
            aileron = (x - width / 2f) / (300F)
            elevator = (height / 2f - y) / (300f)
        }
        onJoystickChange(aileron, elevator)
// will render again the screen.
        invalidate()
    }

}