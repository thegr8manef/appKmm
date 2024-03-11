package com.example.appkmm.src.utils

actual class DeviceInfos(context : Any) {

    actual fun getOsDevice(): String {
        return "iOS"
    }
    actual fun getModel(): String {
        return "iphone"
    }
}