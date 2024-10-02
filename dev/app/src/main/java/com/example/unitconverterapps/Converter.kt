package com.example.unitconverterapps

class Converter {
    fun convert(amount: Double, fromUnit: String, toUnit: String): Double {
        when (fromUnit) {
            "meters" -> {
                when (toUnit) {
                    "feet" -> return amount * 3.28084
                    else -> throw Exception("Unsupported unit conversion")
                }
            }
            "feet" -> {
                when (toUnit) {
                    "meters" -> return amount / 3.28084
                    else -> throw Exception("Unsupported unit conversion")
                }
            }
            "kilograms" -> {
                when (toUnit) {
                    "pounds" -> return amount * 2.20462
                    else -> throw Exception("Unsupported unit conversion")
                }
            }
            "pounds" -> {
                when (toUnit) {
                    "kilograms" -> return amount / 2.20462
                    else -> throw Exception("Unsupported unit conversion")
                }
            }
            "liters" -> {
                when (toUnit) {
                    "gallons" -> return amount * 0.264172
                    else -> throw Exception("Unsupported unit conversion")
                }
            }
            "gallons" -> {
                when (toUnit) {
                    "liters" -> return amount / 0.264172
                    else -> throw Exception("Unsupported unit conversion")
                }
            }
            else -> throw Exception("Unsupported unit conversion")
        }
    }
}