package com.example.appkmm.src.data.local.manager

import com.example.appkmm.src.models.RequestData

// DataManager class acts as a bridge between the application's business logic and the local data storage.
// It provides methods to save and read data using a DataStoreManager instance.
expect class DataManager (context : Any?) {
    suspend fun saveData(key: String, value: RequestData)
    suspend fun readData(key: String): RequestData?

    suspend fun saveCoreLibId(key: String, value: String)
    suspend fun readCoreLibId(key: String): String?

    suspend fun saveBaseUrl(key: String, value: String)
    suspend fun readBaseUrl(key: String): String?

    suspend fun saveMpDeviceIdentifier(key: String, value: String)
    suspend fun readMpDeviceIdentifier(key: String): String?
}

