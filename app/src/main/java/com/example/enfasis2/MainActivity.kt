package com.example.enfasis2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var citasAdapter: CitasAdapter
    private val citas = mutableListOf(
        Cita("Méd. Sara Cárdenas", "Medicina General", "11/12/2024 - 14:00", "Asignada", R.drawable.doctor),
        Cita("Dr. Pedro Gómez", "Pediatría", "12/12/2024 - 10:00", "Asignada", R.drawable.ic_pediatra),
        Cita("Dra. Ana Ruiz", "Dermatología", "15/12/2024 - 16:30", "Asignada", R.drawable.ic_dermatologo)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAgendarCita = findViewById<Button>(R.id.btn_agendar_cita)
        btnAgendarCita.setOnClickListener {
            val intent = Intent(this, AgendarCitaActivity::class.java)
            startActivityForResult(intent, 101) // Se inicia AgendarCitaActivity y se espera un resultado
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_appointments)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Creamos el adaptador
        citasAdapter = CitasAdapter(citas) { cita ->
            citasAdapter.cancelarCita(cita) // Llamamos a cancelarCita cuando se hace click en "Cancelar"
            Toast.makeText(this, "Cita con ${cita.nombreDoctor} cancelada", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter = citasAdapter
    }

    // Recibimos el resultado de AgendarCitaActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            val medico = data?.getStringExtra("medico")
            val fecha = data?.getStringExtra("fecha")

            if (medico != null && fecha != null) {
                // Creamos una nueva cita con los datos recibidos
                val nuevaCita = Cita(medico, "Especialidad", fecha, "Pendiente", R.drawable.ic_doctor)
                citas.add(nuevaCita) // Agregamos la cita a la lista
                citasAdapter.notifyItemInserted(citas.size - 1) // Notificamos al adaptador que se agregó un nuevo ítem
            }
        }
    }
}

