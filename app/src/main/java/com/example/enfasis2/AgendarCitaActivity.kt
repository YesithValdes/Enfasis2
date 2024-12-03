package com.example.enfasis2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AgendarCitaActivity : AppCompatActivity() {

    private var medicoSeleccionado: String? = null
    private var fechaSeleccionada: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendar_cita)

        // Spinner para selección de médico
        val spinnerMedicos = findViewById<Spinner>(R.id.spinner_medicos)
        val medicos = listOf(
            "Dr. Pedro Gómez - Cardiología",
            "Dra. Ana Ruiz - Dermatología",
            "Méd. Sara Cárdenas - Medicina General",
            "Dr. Juan Pérez - Pediatría",
            "Dr. Luis López - Neurología"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            medicos
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerMedicos.adapter = adapter

        // Actualizar médico seleccionado
        spinnerMedicos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                medicoSeleccionado = medicos[position]
                actualizarResumen()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        // Botón para ver disponibilidad
        val btnVerDisponibilidad = findViewById<Button>(R.id.btn_ver_disponibilidad)
        btnVerDisponibilidad.setOnClickListener {
            if (medicoSeleccionado != null) {
                val intent = Intent(this, SeleccionarDisponibilidadActivity::class.java)
                intent.putExtra("medicoSeleccionado", medicoSeleccionado)
                startActivityForResult(intent, 200)
            } else {
                Toast.makeText(this, "Por favor, seleccione un médico primero.", Toast.LENGTH_SHORT).show()
            }
        }


        // Selección de fecha
        val seleccionarFecha = findViewById<LinearLayout>(R.id.select_fecha)
        seleccionarFecha.setOnClickListener {
            val intent = Intent(this, SeleccionarFechaActivity::class.java)
            startActivityForResult(intent, 100)
        }

        // Botón Aceptar
        val btnAceptar = findViewById<Button>(R.id.btn_aceptar)
        btnAceptar.setOnClickListener {
            if (medicoSeleccionado != null && fechaSeleccionada != null) {
                val intent = Intent()
                intent.putExtra("medico", medicoSeleccionado)
                intent.putExtra("fecha", fechaSeleccionada)
                setResult(RESULT_OK, intent) // Devuelve los datos a la actividad principal
                finish() // Cierra esta actividad y regresa a MainActivity
            } else {
                Toast.makeText(this, "Por favor, seleccione un médico y una fecha", Toast.LENGTH_SHORT).show()
            }
        }

    }

    // Manejar el resultado de la selección de fecha
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            fechaSeleccionada = data?.getStringExtra("fecha")
            actualizarResumen()
        }
    }

    private fun actualizarResumen() {
        val resumen = findViewById<TextView>(R.id.tv_seleccion_resumen)
        resumen.text = "Médico: $medicoSeleccionado\nFecha: ${fechaSeleccionada ?: "No seleccionada"}"
    }
}

