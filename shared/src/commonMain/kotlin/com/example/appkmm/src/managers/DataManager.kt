package com.example.appkmm.src.managers

interface DeviceInfo {
    val deviceModel: String
    val sysName: String
    val osVersion: String
    val deviceIdentifierForVendor: String
    val deviceRegion: String
    val timeZone: String
    val screenDpi: String
    val osName: String
    val timestamp: Long
    val appIdentifier: String
    val appVersion: String
    val deviceManufacturer: String
}

expect fun getDeviceInfo(): DeviceInfo