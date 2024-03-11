package com.example.appkmm.src.data.remote.repository

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

actual class Repository actual constructor() : CoreLibApi {

    /**
     * Posts user data to the server.
     * @return A ResponseWS object containing the response from the server.
     */
    @OptIn(InternalAPI::class)
    actual override suspend fun postUserData(data : RequestData): ResponseWS {
        val client = HttpClient()
        val formData = mapOf(
            "action" to "mpRegisterDevice",
            "device_identifier" to "0DEA426A-C06E-42F9-A1B4-F12308089455",
            "app_identifier" to "com.elsevier-masson.gt11",
            "app_version" to "1.3.1",
            "os_version" to "17.3.1",
            "os" to "iOS",
            "device_model" to "iPhone14,3",
            "device_manufacture" to "Apple",
            "device_region" to "en_US",
            "device_timezone" to "1",
            "screen_resolution" to "1242*2688",
            "timestamp" to "1709735813",
            "check" to "944150675E23A25B1C6A4CDE5981EA22",
            "push_additional_params[GTUser]" to "Not_Connected",
            "screen_dpi" to "45454884"
        )
        val parameters = Parameters.build {
            formData.forEach { (key, value) ->
                append(key, value)
            }
        }
        lateinit var postData: HttpResponse
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
        val responseCode: String? = postData.status.value.toString()
        val responseDescription: Map<String, String>? = null
        val responseMessage: Any? = null
        val responseDataObject: ResponseData? = null
        return ResponseWS(responseCode, responseDescription, responseMessage, responseDataObject)
    }
}