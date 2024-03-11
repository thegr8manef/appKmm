package com.example.appkmm.src.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestData(
    @SerialName("action") val action: String,
    @SerialName("device_identifier") val deviceIdentifier: String,
    @SerialName("app_identifier") val appIdentifier: String,
    @SerialName("app_version") val appVersion: String,
    @SerialName("os_version") val osVersion: String,
    @SerialName("os") val os: String,
    @SerialName("device_model") val deviceModel: String,
    @SerialName("device_manufacture") val deviceManufacture: String,
    @SerialName("device_region") val deviceRegion: String,
    @SerialName("device_timezone") val deviceTimezone: String,
    @SerialName("screen_resolution") val screenResolution: String,
    @SerialName("timestamp") val timestamp: String,
    @SerialName("check") val check: String,
    @SerialName("push_additional_params") val pushAdditionalParams: Map<String, String>,
    @SerialName("screen_dpi") val screenDpi: String
)
