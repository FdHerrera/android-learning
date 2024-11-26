package com.fdherrera.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fdherrera.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }

    @Composable
    fun UnitConverter() {
        var inputValue by remember { mutableStateOf("") }
        var outputValue by remember { mutableStateOf("") }
        var inputUnit by remember { mutableStateOf("Meters") }
        var outputUnit by remember { mutableStateOf("Meters") }
        var inputExpanded by remember { mutableStateOf(false) }
        var outputExpanded by remember { mutableStateOf(false) }

        val conversionFactor = remember { mutableDoubleStateOf(1.0) }
        val outputConversionFactor = remember { mutableDoubleStateOf(1.0) }

        fun convertUnits() {
            val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
            val result =
                (inputValueDouble * conversionFactor.doubleValue * 100.0 / outputConversionFactor.doubleValue).roundToInt() / 100.0
            outputValue = result.toString()
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Unit Converter")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text("Enter value") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Box {
                    Button(onClick = { inputExpanded = true }) {
                        Text(inputUnit)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Pick something"
                        )
                    }
                    DropdownMenu(
                        expanded = inputExpanded,
                        onDismissRequest = { inputExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                inputExpanded = false
                                inputUnit = "Centimeters"
                                conversionFactor.doubleValue = 0.01
                                convertUnits()
                            })
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                inputExpanded = false
                                inputUnit = "Meters"
                                conversionFactor.doubleValue = 1.0
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                inputExpanded = false
                                inputUnit = "Feet"
                                conversionFactor.doubleValue = 0.3048
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeters") },
                            onClick = {
                                inputExpanded = false
                                inputUnit = "Millimeters"
                                conversionFactor.doubleValue = 0.001
                                convertUnits()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box {
                    Button(onClick = { outputExpanded = true }) {
                        Text(outputUnit)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Pick something"
                        )
                    }
                    DropdownMenu(
                        expanded = outputExpanded,
                        onDismissRequest = { outputExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Centimeters"
                                outputConversionFactor.doubleValue = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Meters"
                                outputConversionFactor.doubleValue = 1.0
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Feet"
                                outputConversionFactor.doubleValue = 0.3048
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Millimeters") },
                            onClick = {
                                outputExpanded = false
                                outputUnit = "Millimeters"
                                outputConversionFactor.doubleValue = 0.001
                                convertUnits()
                            }
                        )
                    }
                }
            }
            Text("Result: $outputValue")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()
    }
}
