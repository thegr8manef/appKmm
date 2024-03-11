package com.example.appkmm.src.data.local.manager

import com.example.appkmm.src.data.local.database.DataStoreManager
import com.example.appkmm.src.models.RequestData
import platform.Foundation.NSUserDefaults
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class DataManager actual constructor(context : Any?) {
    actual suspend fun saveData(key: String, value: RequestData) {
        val serializedValue = Json.encodeToString(value)
        val defaults = NSUserDefaults.standardUserDefaults
        defaults.setObject(serializedValue, key)
        defaults.synchronize()
    }

    actual suspend fun readData(key: String): RequestData? {
        val defaults = NSUserDefaults.standardUserDefaults
        val serializedValue = defaults.objectForKey(key) as? String
        return serializedValue?.let { Json.decodeFromString(it) }
    }
}

