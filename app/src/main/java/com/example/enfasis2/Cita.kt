package com.example.enfasis2

data class Cita(
    val nombreDoctor: String,
    val especialidad: String,
    val fechaHora: String,
    val estado: String,
    val iconoDoctor: Int // Referencia a un recurso drawable
)
