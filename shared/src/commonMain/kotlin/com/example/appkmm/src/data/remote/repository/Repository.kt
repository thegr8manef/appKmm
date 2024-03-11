package com.example.appkmm.src.data.remote.repository

import com.example.appkmm.src.data.remote.api.CoreLibApi
import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS

expect class Repository() : CoreLibApi {
    override suspend fun postUserData(data : RequestData): ResponseWS
}