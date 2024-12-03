package com.example.enfasis2

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class DisponibilidadAdapter(
    private val context: Context,
    private val todosLosDiasDelMes: List<String>,
    private val fechasDisponibles: List<String>
) : BaseAdapter() {

    override fun getCount(): Int = fechasDisponibles.size

    override fun getItem(position: Int): Any = fechasDisponibles[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_fecha, parent, false)
        }

        val fecha = fechasDisponibles[position]
        val tvFecha = view?.findViewById<TextView>(R.id.tv_fecha)

        tvFecha?.text = fecha

        // Lógica para marcar fechas ocupadas
        if (fecha == "2024-12-10" || fecha == "2024-12-11") { // Aquí puedes marcar las fechas ocupadas
            tvFecha?.setTextColor(Color.RED)
            tvFecha?.setBackgroundColor(Color.LTGRAY)
        } else {
            tvFecha?.setTextColor(Color.BLACK)
            tvFecha?.setBackgroundColor(Color.WHITE)
        }

        return view!!
    }
}
