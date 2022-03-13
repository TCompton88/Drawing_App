package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton


class MainActivity : AppCompatActivity() {

    private  var drawingView: DrawingView? = null
    private var brushChoice = R.layout.dialogue_brush_sizes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(12.toFloat())
    }

    private fun showBrushSizeDialog(){
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialogue_brush_sizes)
        brushDialog.setTitle("Brush Size: ")
        var smallBtn : ImageButton = brushDialog.findViewById(R.id.ib_medium_brush)

    }
}