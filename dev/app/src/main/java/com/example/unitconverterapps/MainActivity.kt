package com.example.unitconverterapps

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.unitconverterapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var editTextAmount: EditText
    private lateinit var textViewResult: TextView
    private lateinit var linearButtons: LinearLayout
    private lateinit var buttonConvert1: Button
    private lateinit var buttonConvert2: Button
    private lateinit var spinnerCategory: Spinner
    private val converter = Converter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextAmount = findViewById(R.id.editTextAmount)
        textViewResult = findViewById(R.id.textViewResult)
        linearButtons = findViewById(R.id.linearButtons)
        buttonConvert1 = findViewById(R.id.buttonConvert1)
        buttonConvert2 = findViewById(R.id.buttonConvert2)
        spinnerCategory = findViewById(R.id.spinnerCategory)

        // Set up the spinner
        val categories = arrayOf("Select Category", "Length", "Weight", "Volume")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = adapter

        spinnerCategory.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                updateConversionButtons(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                linearButtons.visibility = View.GONE
            }
        }
    }

    private fun updateConversionButtons(position: Int) {
        when (position) {
            1 -> { // Length
                buttonConvert1.text = "Convert to Feet"
                buttonConvert2.text = "Convert to Meters"
                linearButtons.visibility = View.VISIBLE

                buttonConvert1.setOnClickListener { convert("meters", "feet") }
                buttonConvert2.setOnClickListener { convert("feet", "meters") }
            }
            2 -> { // Weight
                buttonConvert1.text = "Convert to Pounds"
                buttonConvert2.text = "Convert to Kilograms"
                linearButtons.visibility = View.VISIBLE

                buttonConvert1.setOnClickListener { convert("kilograms", "pounds") }
                buttonConvert2.setOnClickListener { convert("pounds", "kilograms") }
            }
            3 -> { // Volume
                buttonConvert1.text = "Convert to Gallons"
                buttonConvert2.text = "Convert to Liters"
                linearButtons.visibility = View.VISIBLE

                buttonConvert1.setOnClickListener { convert("liters", "gallons") }
                buttonConvert2.setOnClickListener { convert("gallons", "liters") }
            }
            else -> {
                linearButtons.visibility = View.GONE
            }
        }
    }

    private fun convert(fromUnit: String, toUnit: String) {
        val amount = editTextAmount.text.toString().toDoubleOrNull()
        if (amount != null) {
            val result = converter.convert(amount, fromUnit, toUnit)
            textViewResult.text = "$amount $fromUnit = $result $toUnit"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }
}