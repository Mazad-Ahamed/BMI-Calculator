package com.example.bmi_calculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bmi_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Use only ViewBinding to set the content view:
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOne.setOnClickListener {
            val weight = binding.weightEt.text.toString()
            val height = binding.hightEt.text.toString()

            if (validateInput(weight, height)) {
                val bmi = weight.toDouble() / ((height.toDouble() / 100) * (height.toDouble() / 100))
                val bmiDigit = String.format("%.2f", bmi).toDouble()
                displayResult(bmiDigit)
            }
        }
    }

    private fun displayResult(bmiDigit: Double) {
        var result = ""
        var color = 0
        var range = ""

        when {
            bmiDigit < 18.50 -> {
                result = "Underweight"
                color = R.color.Blue
                range = "(Underweight range is less than 18.50)"
            }
            bmiDigit in 18.50..24.99 -> {
                result = "Healthy"
                color = R.color.Green
                range = "(Healthy range is 18.50 - 24.99)"
            }
            bmiDigit in 25.00..29.99 -> {
                result = "Overweight"
                color = R.color.orenge
                range = "(Overweight range is 25.00 - 29.99)"
            }
            bmiDigit > 29.99 -> {
                result = "Obese"
                color = R.color.Red
                range = "(Obese range is greater than 29.99)"
            }
        }

        binding.textView.setTextColor(ContextCompat.getColor(this, color))
        binding.textView.text = bmiDigit.toString()
        binding.textView2.setTextColor(ContextCompat.getColor(this, color))
        binding.textView2.text = result
        binding.textView3.setTextColor(ContextCompat.getColor(this, color))  // fixed typo here
        binding.textView3.text = range
    }

    private fun validateInput(weight: String, height: String): Boolean {
        return when {
            weight.isEmpty() -> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                false
            }
            height.isEmpty() -> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                false
            }
            else -> true
        }
    }
}
