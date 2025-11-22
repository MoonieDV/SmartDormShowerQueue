package com.example.smarthomeshowerqueue.view

interface HomeView {
    fun showShowers(showers: List<com.example.smarthomeshowerqueue.model.Shower>)
    fun showQueue(users: List<com.example.smarthomeshowerqueue.model.User>)
    fun updateTime(time: String)
    fun notifyUser()
    fun showHistoryLogs(logs: List<com.example.smarthomeshowerqueue.model.HistoryLog>)
}