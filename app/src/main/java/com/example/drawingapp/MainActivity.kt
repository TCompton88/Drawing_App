package com.example.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get


class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var imageButtonCurrentColor : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(15.toFloat())

        val linearLayoutColors = findViewById<LinearLayout>(R.id.ll_paint_colors)

        val brushIcon: ImageButton = findViewById(R.id.ib_brush)
        brushIcon.setOnClickListener {
            showBrushSizeDialog()
        }

        imageButtonCurrentColor = linearLayoutColors[1] as ImageButton
        imageButtonCurrentColor!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_selected)
        )
    }

    private fun showBrushSizeDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialogue_brush_sizes)
        brushDialog.setTitle("Brush Size: ")
        var smallBtn: ImageButton = brushDialog.findViewById(R.id.ib_small_brush)
        smallBtn.setOnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }

        var mediumBtn: ImageButton = brushDialog.findViewById(R.id.ib_medium_brush)
        mediumBtn.setOnClickListener {
            drawingView?.setSizeForBrush(18.toFloat())
            brushDialog.dismiss()
        }

        var largeBtn: ImageButton = brushDialog.findViewById(R.id.ib_large_brush)
        largeBtn.setOnClickListener {
            drawingView?.setSizeForBrush(24.toFloat())
            brushDialog.dismiss()
        }

        brushDialog.show()
    }

    fun paintClicked(view: View){
        //Toast.makeText(this, "clicked paint", Toast.LENGTH_LONG).show()
        if(view !== imageButtonCurrentColor){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_selected)
            )

            imageButtonCurrentColor!!.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            imageButtonCurrentColor = view

            Toast.makeText(this, "$colorTag", Toast.LENGTH_LONG).show()
        }
    }
}