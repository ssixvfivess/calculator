package com.example.calculator

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonAc.setOnClickListener{
            binding.input.text = ""
            binding.output.text = ""
        }

        binding.button0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }

        binding.buttonComma.setOnClickListener {
            binding.input.text = addToInputText(".")
        }

        binding.buttonEquals.setOnClickListener {
            showResult()
        }

        binding.button1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }

        binding.button2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }

        binding.button3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }

        binding.buttonPlus.setOnClickListener {
            binding.input.text = addToInputText("+")
        }

        binding.button4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }

        binding.button5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }


        binding.button6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }

        binding.buttonMinus.setOnClickListener {
            binding.input.text = addToInputText("-")
        }

        binding.button7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }

        binding.button8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }

        binding.button9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }

        binding.buttonMultiplication.setOnClickListener {
            binding.input.text = addToInputText("×")
        }

        binding.buttonC.setOnClickListener {
            binding.input.text = binding.input.text.dropLast(1)
        }

        binding.buttonOpen.setOnClickListener {
            binding.input.text = addToInputText("(")
        }

        binding.buttonClose.setOnClickListener {
            binding.input.text = addToInputText(")")
        }

        binding.buttonDivide.setOnClickListener {
            binding.input.text = addToInputText("÷")
        }

    }

    private fun addToInputText(value: String): String{
        return binding.input.text.toString() + value
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = Expression(expression).calculate()
            if (result.isNaN()) {
                // Show Error Message
                binding.output.text = "Error"
            } else {
                // Show Result
                binding.output.text = DecimalFormat("0.######").format(result).toString()
            }
        } catch (e: Exception) {
            // Show Error Message
            binding.output.text = "Error"
        }
    }
}