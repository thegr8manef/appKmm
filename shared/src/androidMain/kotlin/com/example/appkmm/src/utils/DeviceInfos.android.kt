package com.example.appkmm.src.utils

import android.content.Context
import android.os.Build

actual class DeviceInfos actual constructor(context : Any) {
    val androidContext = context as? Context

    actual fun getOsDevice(): String {
        return "android"
    }
    actual fun getModel(): String {
        return Build.MODEL
    }
}