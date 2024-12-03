package com.example.enfasis2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CitasAdapter(
    private val citas: MutableList<Cita>, // Cambiado a MutableList
    private val onCancelClick: (Cita) -> Unit
) : RecyclerView.Adapter<CitasAdapter.CitaViewHolder>() {

    class CitaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val doctorIcon: ImageView = view.findViewById(R.id.doctor_icon)
        val doctorName: TextView = view.findViewById(R.id.doctor_name)
        val doctorSpecialty: TextView = view.findViewById(R.id.doctor_specialty)
        val appointmentDate: TextView = view.findViewById(R.id.appointment_date)
        val appointmentStatus: TextView = view.findViewById(R.id.appointment_status)
        val cancelButton: Button = view.findViewById(R.id.btn_cancel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_citas, parent, false)
        return CitaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CitaViewHolder, position: Int) {
        val cita = citas[position]
        holder.doctorIcon.setImageResource(cita.iconoDoctor)
        holder.doctorName.text = cita.nombreDoctor
        holder.doctorSpecialty.text = cita.especialidad
        holder.appointmentDate.text = cita.fechaHora
        holder.appointmentStatus.text = cita.estado

        // Cambia el color del estado según el texto
        holder.appointmentStatus.setTextColor(
            if (cita.estado == "Asignada") android.graphics.Color.parseColor("#00C853")
            else android.graphics.Color.parseColor("#FF5252")
        )

        // Evento del botón de cancelar
        holder.cancelButton.setOnClickListener {
            // Llamamos al callback y eliminamos la cita de la lista
            onCancelClick(cita)
        }
    }

    override fun getItemCount(): Int = citas.size

    // Método para eliminar la cita de la lista y actualizar la interfaz
    fun cancelarCita(cita: Cita) {
        val position = citas.indexOf(cita)
        if (position >= 0) {
            citas.removeAt(position) // Elimina la cita de la lista
            notifyItemRemoved(position) // Notifica que el item ha sido eliminado
        }
    }
}

