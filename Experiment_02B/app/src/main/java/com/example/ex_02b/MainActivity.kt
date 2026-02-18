package com.example.ex_02b

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var lastNumeric: Boolean = false
    private var stateError: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                onDigit(it as Button)
            }
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener { onDecimalPoint() }
        findViewById<Button>(R.id.btnC).setOnClickListener { onClear() }
        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperator("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { onOperator("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { onOperator("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { onOperator("/") }
        findViewById<Button>(R.id.btnEqual).setOnClickListener { onEqual() }

        findViewById<Button>(R.id.btnSin).setOnClickListener { onScientific("sin") }
        findViewById<Button>(R.id.btnCos).setOnClickListener { onScientific("cos") }
        findViewById<Button>(R.id.btnTan).setOnClickListener { onScientific("tan") }
        findViewById<Button>(R.id.btnLog).setOnClickListener { onScientific("log") }
        findViewById<Button>(R.id.btnSqrt).setOnClickListener { onScientific("sqrt") }
    }

    private fun onDigit(button: Button) {
        if (stateError) {
            tvDisplay.text = button.text
            stateError = false
        } else {
            if (tvDisplay.text.toString() == "0") {
                tvDisplay.text = button.text
            } else {
                tvDisplay.append(button.text)
            }
        }
        lastNumeric = true
    }

    private fun onDecimalPoint() {
        if (lastNumeric && !stateError && !lastDot) {
            tvDisplay.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun onClear() {
        tvDisplay.text = "0"
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    private fun onOperator(operator: String) {
        if (lastNumeric && !stateError) {
            tvDisplay.append(operator)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun onScientific(func: String) {
        if (lastNumeric && !stateError) {
            val value = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
            val result = when (func) {
                "sin" -> sin(Math.toRadians(value))
                "cos" -> cos(Math.toRadians(value))
                "tan" -> tan(Math.toRadians(value))
                "log" -> log10(value)
                "sqrt" -> sqrt(value)
                else -> 0.0
            }
            tvDisplay.text = result.toString()
            lastNumeric = true
            lastDot = result.toString().contains(".")
        }
    }

    private fun onEqual() {
        if (lastNumeric && !stateError) {
            val text = tvDisplay.text.toString()
            // This is a very basic evaluation for demonstration.
            // For a real app, use an expression evaluator library or a proper parser.
            try {
                // Simplified evaluation for single operator expressions
                val result = evaluateSimpleExpression(text)
                tvDisplay.text = result.toString()
                lastDot = tvDisplay.text.contains(".")
            } catch (e: Exception) {
                tvDisplay.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }

    private fun evaluateSimpleExpression(expression: String): Double {
        return when {
            expression.contains("+") -> {
                val parts = expression.split("+")
                parts[0].toDouble() + parts[1].toDouble()
            }
            expression.contains("-") -> {
                val parts = expression.split("-")
                parts[0].toDouble() - parts[1].toDouble()
            }
            expression.contains("*") -> {
                val parts = expression.split("*")
                parts[0].toDouble() * parts[1].toDouble()
            }
            expression.contains("/") -> {
                val parts = expression.split("/")
                parts[0].toDouble() / parts[1].toDouble()
            }
            else -> expression.toDouble()
        }
    }
}
