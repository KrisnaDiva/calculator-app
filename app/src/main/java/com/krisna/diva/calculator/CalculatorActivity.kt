package com.krisna.diva.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var addButton: Button
    private lateinit var subButton: Button
    private lateinit var mulButton: Button
    private lateinit var divButton: Button
    private lateinit var modButton: Button
    private lateinit var clearButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var calculationTextView: TextView

    private val add = '+'
    private val sub = '-'
    private val mul = '×'
    private val div = '/'
    private val mod = '%'

    private fun initComponents() {
        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        addButton = findViewById(R.id.addButton)
        subButton = findViewById(R.id.subButton)
        mulButton = findViewById(R.id.mulButton)
        divButton = findViewById(R.id.divButton)
        modButton = findViewById(R.id.modButton)
        clearButton = findViewById(R.id.clearButton)
        resultTextView = findViewById(R.id.resultTextView)
        calculationTextView = findViewById(R.id.calculationTextView)
    }

    private fun initListeners() {
        addButton.setOnClickListener { calculate(add) }
        subButton.setOnClickListener { calculate(sub) }
        mulButton.setOnClickListener { calculate(mul) }
        divButton.setOnClickListener { calculate(div) }
        modButton.setOnClickListener { calculate(mod) }
        clearButton.setOnClickListener { clear() }
    }

    private fun calculate(operation: Char) {
        val num1 = editText1.text.toString().toDoubleOrNull()
        val num2 = editText2.text.toString().toDoubleOrNull()

        if (num1 != null && num2 != null) {
            val result: Any? = when (operation) {
                add -> num1 + num2
                sub -> num1 - num2
                mul -> num1 * num2
                div -> if (num2 != 0.0) num1 / num2 else resources.getString(R.string.canNotDevideByZero)
                mod -> if (num2 != 0.0) num1 % num2 else resources.getString(R.string.canNotDevideByZero)
                else -> null
            }

            resultTextView.text = resources.getString(R.string.resultTextView)
            calculationTextView.text = resources.getString(
                R.string.calculationTextView,
                printDouble(num1),
                operation,
                printDouble(num2),
                printDouble(result)
            )
        }

    }

    private fun printDouble(value: Any?): String {
        return if (value is Double && value % 1 == 0.0) {
            value.toInt().toString()
        } else {
            value.toString()
        }
    }

    private fun clear() {
        resultTextView.text = resources.getString(R.string.blank)
        calculationTextView.text = resources.getString(R.string.blank)
        editText1.text.clear()
        editText2.text.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator)

        initComponents()
        initListeners()
    }

}