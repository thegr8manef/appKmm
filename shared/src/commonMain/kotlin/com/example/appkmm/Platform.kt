package com.example.appkmm

interface Platform {
    val model: String
}

expect fun getPlatform(): Platform