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

                buttonConvert1.setOnClickListener { convertToFeet() }
                buttonConvert2.setOnClickListener { convertToMeters() }
            }
            2 -> { // Weight
                buttonConvert1.text = "Convert to Pounds"
                buttonConvert2.text = "Convert to Kilograms"
                linearButtons.visibility = View.VISIBLE

                buttonConvert1.setOnClickListener { convertKgToLb() }
                buttonConvert2.setOnClickListener { convertLbToKg() }
            }
            3 -> { // Volume
                buttonConvert1.text = "Convert to Gallons"
                buttonConvert2.text = "Convert to Liters"
                linearButtons.visibility = View.VISIBLE

                buttonConvert1.setOnClickListener { convertLitersToGallons() }
                buttonConvert2.setOnClickListener { convertGallonsToLiters() }
            }
            else -> {
                linearButtons.visibility = View.GONE
            }
        }
    }

    private fun convertToFeet() {
        val meters = editTextAmount.text.toString().toDoubleOrNull()
        if (meters != null) {
            val feet = meters * 3.28084
            textViewResult.text = "$meters meters = $feet feet"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }

    private fun convertToMeters() {
        val feet = editTextAmount.text.toString().toDoubleOrNull()
        if (feet != null) {
            val meters = feet / 3.28084
            textViewResult.text = "$feet feet = $meters meters"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }

    private fun convertKgToLb() {
        val kg = editTextAmount.text.toString().toDoubleOrNull()
        if (kg != null) {
            val pounds = kg * 2.20462
            textViewResult.text = "$kg kilograms = $pounds pounds"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }

    private fun convertLbToKg() {
        val lb = editTextAmount.text.toString().toDoubleOrNull()
        if (lb != null) {
            val kg = lb / 2.20462
            textViewResult.text = "$lb pounds = $kg kilograms"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }

    private fun convertLitersToGallons() {
        val liters = editTextAmount.text.toString().toDoubleOrNull()
        if (liters != null) {
            val gallons = liters * 0.264172
            textViewResult.text = "$liters liters = $gallons gallons"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }

    private fun convertGallonsToLiters() {
        val gallons = editTextAmount.text.toString().toDoubleOrNull()
        if (gallons != null) {
            val liters = gallons / 0.264172
            textViewResult.text = "$gallons gallons = $liters liters"
        } else {
            textViewResult.text = "Please enter a valid number"
        }
    }
}