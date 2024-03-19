package com.example.appkmm.src.data.remote.api

import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS


interface CoreLibApi {

    /**
     * Sends user data to the server.
     * @return A ResponseWS response indicating the success or failure of the operation.
     */
    suspend fun postUserData(data : RequestData) : ResponseWS
    suspend fun getUpdateDeviceInfo(
        action: String?,
        mpDeviceIdentifier: String?,
        appVersion: String?,
        osVersion: String?,
        deviceRegion: String?,
        deviceTimezone: String?,
        deviceIdentifier: String?, timestamp: String?, check: String?,
        deviceInfo: Map<String?, String?>?, additionalParams: Map<String?, String?>?
    ) : ResponseWS

}