package com.example.appkmm.src.data.remote.repository

import android.util.Log
import com.example.appkmm.src.data.remote.api.CoreLibApi
import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseData
import com.mobelite.corelibkmm.src.models.ResponseWS
import com.mobelite.corelibkmm.src.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.runBlocking

actual class Repository actual constructor() : CoreLibApi {
    private val TAG = "Android Repository"
    val client = HttpClient()
    lateinit var postData: HttpResponse
    lateinit var result : ResponseWS
    /**
     * Posts user data to the server.
     * @return A ResponseWS object containing the response from the server.
     */
    @OptIn(InternalAPI::class)
    actual override suspend fun postUserData(data: RequestData): ResponseWS {
        runBlocking {
            val formData = mapOf(
                "action" to data.action,
                "device_identifier" to data.deviceIdentifier,
                "app_identifier" to data.appIdentifier,
                "app_version" to data.appVersion,
                "os_version" to data.osVersion,
                "os" to data.os,
                "device_model" to data.deviceModel,
                "device_manufacture" to data.deviceManufacture,
                "device_region" to data.deviceRegion,
                "device_timezone" to data.deviceTimezone,
                "screen_resolution" to data.screenResolution,
                "timestamp" to data.timestamp,
                "check" to data.check,
                "push_additional_params[${data.pushAdditionalParams.keys}]" to "${data.pushAdditionalParams.values}",
                "screen_dpi" to data.screenDpi
            )
            Log.w(TAG,"the data is :$formData" )
            val parameters = Parameters.build {
                formData.forEach { (key, value) ->
                    append(key, value)
                }
            }
            try {
                postData = client.post(Constants.BASE_URL) {
                    body = FormDataContent(parameters)
                    contentType(ContentType.Application.FormUrlEncoded)
                }
            } catch (e: ClientRequestException) {
                println("Error: ${e.response.status.description}")
            } finally {
                client.close()
            }
            Log.w(TAG,"the result is :${postData}" )
            val responseCode: String? = postData.status.value.toString()
            val responseDescription: Map<String, String>? = null
            val responseMessage: Any? = null
            val responseDataObject: ResponseData? = null
            result = ResponseWS(
                responseCode,
                responseDescription,
                responseMessage,
                responseDataObject
            )
        }
        return result
    }
}