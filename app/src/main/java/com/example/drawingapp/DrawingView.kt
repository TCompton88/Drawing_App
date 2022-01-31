package com.example.drawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    //Variables to access Bitmap and canvas and draw functions of android
    private var drawPath : CustomPath? = null
    private var canvasBitmap : Bitmap? = null
    private var drawPaint: Paint? = null
    private var canvasPaint: Paint? = null
    private var brushSize: Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas: Canvas? = null
    private  val paths = ArrayList<CustomPath>()

    //initializes this class to set up drawing
    init {
        setUpDrawing()
    }

    private fun setUpDrawing(){
        drawPaint = Paint()
        drawPath = CustomPath(color, brushSize)
        drawPaint!!.color = color
        drawPaint!!.style = Paint.Style.STROKE
        drawPaint!!.strokeJoin = Paint.Join.ROUND
        drawPaint!!.strokeCap = Paint.Cap.ROUND
        canvasPaint = Paint(Paint.DITHER_FLAG)
        brushSize = 20.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(canvasBitmap!!)
    }

    // Change Canvas to Canvas? if it fails
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap!!, 0f, 0f, canvasPaint)

        for (path in paths){
            drawPaint!!.strokeWidth = path.brushSize
            drawPaint!!.color = path.color
            canvas.drawPath(path, drawPaint!!)
        }
        
        if(!drawPath!!.isEmpty) {
            drawPaint!!.strokeWidth = drawPath!!.brushSize
            drawPaint!!.color = drawPath!!.color
            canvas.drawPath(drawPath!!, drawPaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN ->{
                drawPath!!.color = color
                drawPath!!.brushSize = brushSize

                drawPath!!.reset()
                if (touchX != null) {
                    if (touchY != null) {
                        drawPath!!.moveTo(touchX, touchY)
                    }
                }
            }

            MotionEvent.ACTION_MOVE ->{
                if (touchX != null) {
                    if (touchY != null) {
                        drawPath!!.lineTo(touchX, touchY)
                    }
                }
            }

            MotionEvent.ACTION_UP ->{
                paths.add(drawPath!!)
                drawPath = CustomPath(color, brushSize)
            }
            else -> return false
        }
        invalidate()

        return true
    }

    internal inner class CustomPath(var color: Int,
                                    var brushSize: Float) : Path() {

    }
}