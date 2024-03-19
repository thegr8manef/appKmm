package com.example.appkmm.src.data.remote.repository

import com.example.appkmm.src.data.remote.api.CoreLibApi
import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS

expect class Repository(context : Any?) : CoreLibApi {

    /**
     * Sends user data to the server.
     * @return A ResponseWS response indicating the success or failure of the operation.
     */
    override suspend fun postUserData(data : RequestData): ResponseWS

    override suspend fun getUpdateDeviceInfo(
        action: String?,
        mpDeviceIdentifier: String?,
        appVersion: String?,
        osVersion: String?,
        deviceRegion: String?,
        deviceTimezone: String?,
        deviceIdentifier: String?,
        timestamp: String?,
        check: String?,
        deviceInfo: Map<String?, String?>?,
        additionalParams: Map<String?, String?>?
    ): ResponseWS
}