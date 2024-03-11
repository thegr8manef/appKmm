package com.example.appkmm

class AndroidPlatform : Platform {
    override val model: String =  android.os.Build.MODEL

}

actual fun getPlatform(): Platform = AndroidPlatform()