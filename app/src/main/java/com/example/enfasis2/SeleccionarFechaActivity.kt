package com.example.enfasis2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SeleccionarFechaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_fecha)

        val calendarView = findViewById<CalendarView>(R.id.calendar_view)
        calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val fechaSeleccionada = "$dayOfMonth/${month + 1}/$year"
            Toast.makeText(this, "Fecha seleccionada: $fechaSeleccionada", Toast.LENGTH_SHORT).show()

            // Devolver la fecha seleccionada como resultado
            val resultIntent = Intent()
            resultIntent.putExtra("fecha", fechaSeleccionada)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
