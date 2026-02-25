package com.example.ex

import android.os.Bundle
import android.view.GestureDetector
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.MotionEvent
import android.view.View
import android.content.Intent
import kotlin.math.abs


class MainActivity : AppCompatActivity() {
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tv = findViewById<TextView>(R.id.tv)
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onLongPress(e: MotionEvent) {
                tv.text = "Long press"
                Toast.makeText(
                    this@MainActivity,
                    "Long press",
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                val diffX = e2.x - (e1?.x ?: 0f)
                if (abs(diffX) > 50 && abs(velocityX) > 100) {
                    if (diffX < 0) {
                        tv.text = getString(R.string.right_swipe)
                        Toast.makeText(this@MainActivity, "Right Swipe", Toast.LENGTH_SHORT).show()
                    } else {
                        tv.text = getString(R.string.left_swipe)
                        Toast.makeText(this@MainActivity, "Left Swipe", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

            override fun onDoubleTap(e: MotionEvent): Boolean {
                tv.text = "Double tap"
                Toast.makeText(
                    this@MainActivity,
                    "Double tap",
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                return true
            }



        })
        tv.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }
    }
}
