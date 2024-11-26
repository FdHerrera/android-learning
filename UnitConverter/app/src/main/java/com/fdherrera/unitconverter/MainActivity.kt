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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fdherrera.unitconverter.ui.theme.UnitConverterTheme

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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Unit Converter")
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { /*What should happen when the value changes?*/ }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Box {
                    Button(onClick = {}) {
                        Text("Select")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Pick something"
                        )
                    }
                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = {}
                    ) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {})
                        DropdownMenuItem(text = { Text("Meters") }, onClick = {})
                        DropdownMenuItem(text = { Text("Feet") }, onClick = {})
                        DropdownMenuItem(text = { Text("Milimiters") }, onClick = {})
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box {
                    Button(onClick = {}) {
                        Text("Select")
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Pick something"
                        )
                    }
                    DropdownMenu(
                        expanded = false,
                        onDismissRequest = {}
                    ) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {})
                        DropdownMenuItem(text = { Text("Meters") }, onClick = {})
                        DropdownMenuItem(text = { Text("Feet") }, onClick = {})
                        DropdownMenuItem(text = { Text("Milimiters") }, onClick = {})
                    }
                }
            }
            Text("Result:")
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()
    }
}
