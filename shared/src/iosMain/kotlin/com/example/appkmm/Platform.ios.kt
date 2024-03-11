package com.example.appkmm

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val model: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()