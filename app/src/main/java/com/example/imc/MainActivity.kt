package com.example.imc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow

internal class MainActivity : AppCompatActivity() {
    private lateinit var editWeight: EditText
    private lateinit var editHeight: EditText
    private lateinit var calculateButton: Button
    private lateinit var imcResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        createComponents()

        calculateButton.setOnClickListener {
            calculateIMC()
        }
    }

    private fun createComponents() {
        editWeight = findViewById(R.id.editWeight)
        editHeight = findViewById(R.id.editHeight)
        calculateButton = findViewById(R.id.calculateButton)
        imcResult = findViewById(R.id.imcResult)
    }

    private fun calculateIMC() {
        val weight: Double? = editWeight.text.toString().toDoubleOrNull()
        val height: Double? = editHeight.text.toString().toDoubleOrNull()

        if (weight == null) {
            editWeight.error = "O peso deve ser informado!"
            return
        } else if (height == null) {
            editHeight.error = "A altura deve ser informada!"
            return
        }

        val result: Double = weight / height.pow(2.0)
        imcResult.text = "%.1f".format(result)

        clear()
    }

    private fun clear() {
        editWeight.setText("")
        editHeight.setText("")
        editWeight.requestFocus()
    }
}