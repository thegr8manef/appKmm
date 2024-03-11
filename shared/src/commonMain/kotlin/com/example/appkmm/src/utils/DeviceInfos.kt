package com.example.appkmm.src.utils

expect class DeviceInfos(context : Any) {
    fun getModel() : String
    fun getOsDevice() : String
}