package com.example.ex_02a

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.LinearLayout
import android.widget.EditText
import android.widget.Button



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val num1 : EditText = findViewById(R.id.editTextText3)
        val num2 : EditText = findViewById(R.id.editTextText4)
        val result : EditText = findViewById(R.id.editTextText5)
        val add : Button = findViewById(R.id.button3)
        val sub : Button = findViewById(R.id.button6)
        val mul : Button = findViewById(R.id.button7)
        val div : Button = findViewById(R.id.button8)
        val mod : Button = findViewById(R.id.button9)
        val clear : Button = findViewById(R.id.button10)
        clear.setOnClickListener {
            num1.setText("")
            num2.setText("")
            result.setText("")
        }
        add.setOnClickListener {
            val n1 = num1.text.toString().toInt()
            val n2 = num2.text.toString().toInt()
            val sum = n1 + n2
            result.setText(sum.toString())
        }
        sub.setOnClickListener {
            val n1 = num1.text.toString().toInt()
            val n2 = num2.text.toString().toInt()
            val sub = n1-n2
            result.setText(sub.toString())
        }
        mul.setOnClickListener {
            val n1 = num1.text.toString().toInt()
            val n2 = num2.text.toString().toInt()
            val mul = n1*n2
            result.setText(mul.toString())

        }
        div.setOnClickListener {
            val n1 = num1.text.toString().toInt()
            val n2 = num2.text.toString().toInt()
            val div = n1/n2
            result.setText(div.toString())

        }
        mod.setOnClickListener {
            val n1 = num1.text.toString().toInt()
            val n2 = num2.text.toString().toInt()
            val mod = n1%n2
            result.setText(mod.toString())
        }
    }
}
