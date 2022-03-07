package com.example.taxcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taxcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Using the button id, call a function to calculate user's tax
        binding.calculateTaxBtn.setOnClickListener {
            calculateTax()
        }
    }

    private fun calculateTax() {
        // Get the user's annual income
        val stringInTextField = binding.annualIncome.text.toString()
        // Convert string annual income input to Double format
        val annualIncome = stringInTextField.toDouble()

        // Get the user's tax bracket using a when statement
        val selectedTaxBracket = binding.taxBracket.checkedRadioButtonId
        val taxBracket = when (selectedTaxBracket) {
            R.id.tenPercent -> 0.10
            R.id.twelvePercent -> 0.12
            R.id.twentyTwoPercent -> 0.22
            R.id.twentyFourPercent -> 0.24
            R.id.thirtyTwoPercent -> 0.32
            R.id.thirtyFivePercent -> 0.35
            else -> 0.37
        }

        // Calculate the tax and round it up
        var tax = annualIncome * taxBracket
        tax =  kotlin.math.ceil(tax)

        // Add currency formatting
        val formattedTax = NumberFormat.getCurrencyInstance().format(tax)

        // Display the tax to the user
        binding.resultantTax.text = formattedTax

    }
}