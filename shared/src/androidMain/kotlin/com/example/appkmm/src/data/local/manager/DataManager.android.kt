package com.example.appkmm.src.data.local.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.appkmm.src.models.RequestData
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

actual class DataManager actual constructor(context: Any?)  {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "CoreLib")
    }

    //private val Context.dataStore by preferencesDataStore("CoreLib")
    val androidContext = context as? Context
    actual suspend fun saveData(key: String, value: RequestData) {
        val serializedValue = Json.encodeToString(value)
         androidContext?.dataStore?.edit { preferences ->
            preferences[stringPreferencesKey(key)] = serializedValue
        }
    }


     actual suspend fun readData(key: String): RequestData? {
        val serializedValue = androidContext?.dataStore?.data?.firstOrNull()?.get(stringPreferencesKey(key))
        return serializedValue?.let { Json.decodeFromString(it) }
    }

}