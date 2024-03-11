package com.example.appkmm.src.data.local.manager

import com.example.appkmm.src.models.RequestData

expect class DataManager (context : Any?) {
    suspend fun saveData(key: String, value: RequestData)
    suspend fun readData(key: String): RequestData?
}

