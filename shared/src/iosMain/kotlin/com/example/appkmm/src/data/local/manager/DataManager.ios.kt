package com.example.appkmm.src.data.local.manager

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

    actual suspend fun saveCoreLibId(key: String, value: String) {
        val defaults = NSUserDefaults.standardUserDefaults
        defaults.setObject(value, key)
        defaults.synchronize()
    }

    actual suspend fun readCoreLibId(key: String): String? {
        val defaults = NSUserDefaults.standardUserDefaults
        return defaults.objectForKey(key) as? String
    }

    actual suspend fun saveBaseUrl(key: String, value: String) {
        val defaults = NSUserDefaults.standardUserDefaults
        defaults.setObject(value, key)
        defaults.synchronize()
    }

    actual suspend fun readBaseUrl(key: String): String? {
        val defaults = NSUserDefaults.standardUserDefaults
        return defaults.objectForKey(key) as? String
    }

    actual suspend fun saveMpDeviceIdentifier(key: String, value: String) {
        val defaults = NSUserDefaults.standardUserDefaults
        defaults.setObject(value, key)
        defaults.synchronize()
    }

    actual suspend fun readMpDeviceIdentifier(key: String): String? {
        val defaults = NSUserDefaults.standardUserDefaults
        return defaults.objectForKey(key) as? String
    }
}


