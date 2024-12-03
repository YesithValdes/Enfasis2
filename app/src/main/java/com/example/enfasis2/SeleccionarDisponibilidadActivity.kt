package com.example.enfasis2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SeleccionarDisponibilidadActivity : AppCompatActivity() {

    private val medicosDisponibles = mapOf(
        "Dr. Pedro Gómez - Cardiología" to listOf(
            "2024-12-01 10:00", "2024-12-01 14:00", "2024-12-02 09:00", "2024-12-03 10:00",
            "2024-12-05 15:00", "2024-12-07 11:00", "2024-12-09 09:00", "2024-12-10 14:00"
        ),
        "Dra. Ana Ruiz - Dermatología" to listOf(
            "2024-12-02 08:00", "2024-12-04 12:00", "2024-12-06 10:00", "2024-12-08 14:00",
            "2024-12-10 15:00", "2024-12-12 11:00", "2024-12-14 09:00", "2024-12-16 16:00"
        ),
        "Méd. Sara Cárdenas - Medicina General" to listOf(
            "2024-12-01 08:00", "2024-12-03 13:00", "2024-12-05 09:00", "2024-12-07 10:00",
            "2024-12-09 15:00", "2024-12-11 08:00", "2024-12-13 14:00", "2024-12-15 13:00",
            "2024-12-17 16:00"
        ),
        "Dr. Juan Pérez - Pediatría" to listOf(
            "2024-12-01 08:00", "2024-12-03 12:00", "2024-12-04 15:00", "2024-12-06 11:00",
            "2024-12-08 10:00", "2024-12-10 14:00", "2024-12-12 09:00", "2024-12-14 16:00"
        ),
        "Dr. Luis López - Neurología" to listOf(
            "2024-12-02 09:00", "2024-12-05 13:00", "2024-12-07 10:00", "2024-12-09 15:00",
            "2024-12-11 08:00", "2024-12-13 14:00", "2024-12-15 11:00", "2024-12-17 16:00"
        )
    )

    private lateinit var gridView: GridView
    private var medicoSeleccionado: String? = null
    private var fechasDisponibles = mutableListOf<String>()
    private val todosLosDiasDelMes = generateDatesForDecember()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_disponibilidad)

        // Obtener el médico seleccionado desde el Intent
        medicoSeleccionado = intent.getStringExtra("medicoSeleccionado")

        // Obtener las fechas disponibles para ese médico
        medicoSeleccionado?.let {
            fechasDisponibles = medicosDisponibles[it]?.toMutableList() ?: mutableListOf()
        }

        // Mostrar todos los días del mes en el GridView
        gridView = findViewById(R.id.grid_view_calendario)

        // Configurar el adaptador para el GridView
        val adapter = DisponibilidadAdapter(this, todosLosDiasDelMes, fechasDisponibles)
        gridView.adapter = adapter

        // Listener para seleccionar un día
        gridView.setOnItemClickListener { parent, view, position, id ->
            val fechaSeleccionada = todosLosDiasDelMes[position]

            // Verificar si la fecha seleccionada está disponible
            if (fechasDisponibles.contains(fechaSeleccionada)) {
                val intent = Intent()
                intent.putExtra("fechaSeleccionada", fechaSeleccionada)
                setResult(RESULT_OK, intent)
                finish()
            } else {
                Toast.makeText(this, "Fecha ocupada", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón de regresar
        findViewById<Button>(R.id.btn_regresar).setOnClickListener {
            finish() // Regresar a la actividad anterior
        }
    }

    private fun generateDatesForDecember(): List<String> {
        val dates = mutableListOf<String>()
        for (i in 1..31) {
            dates.add("2024-12-${i.toString().padStart(2, '0')}")
        }
        return dates
    }
}
