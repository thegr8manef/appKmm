package com.example.appkmm.src.managers

import android.os.Build

import java.util.TimeZone

class AndroidDeviceInfo: DeviceInfo {

    override val deviceModel: String = Build.MODEL
    override val sysName: String = "Android"
    override val osVersion: String = Build.VERSION.RELEASE
    override val deviceIdentifierForVendor: String = "0DEA426A-C06E-42F9-A1B4-F12308089455"
    override val deviceRegion: String = "This should be changed"
    //context.resources.configuration.locale.country ?: "N/A"

    override val timeZone: String = TimeZone.getDefault().id

    override val screenDpi: String = "45454884"
    override val osName: String = "Android"
    override val timestamp: Long = 1709735813 //TODO change this
    override val appIdentifier: String = "com.elsevier-masson.gt11"
    override val appVersion: String = "1.3.1"
    override val deviceManufacturer: String = Build.MANUFACTURER

}

actual fun getDeviceInfo(): DeviceInfo = AndroidDeviceInfo()