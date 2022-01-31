package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drawingapp.R.layout.dialogue_brush_sizes


class MainActivity : AppCompatActivity() {

    private  var drawingView: DrawingView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())
    }

    private fun showBrushSizeDialog(){
        var brushDialog = Dialog(this)
        brushDialog.setContentView(dialogue_brush_sizes)
        brushDialog.setTitle("Brush Size: ")
    }
}