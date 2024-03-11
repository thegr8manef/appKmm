package com.example.appkmm.src.data.remote.api

import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS


interface CoreLibApi {
    suspend fun postUserData(data : RequestData) : ResponseWS
}