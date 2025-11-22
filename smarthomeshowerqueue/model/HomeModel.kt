package com.example.smarthomeshowerqueue.model

data class Shower(val id: String, val isOccupied: Boolean)
data class User(val name: String)
data class HistoryLog(
    val key: String = "",
    val status: String = "",
    val timestamp: String = "",
    val date: String = "" // Can be extracted from timestamp if needed
)