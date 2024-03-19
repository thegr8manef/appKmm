package com.example.appkmm.src.data.remote.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.appkmm.src.data.local.manager.DataManager
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
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

actual class Repository actual constructor(private val context : Any?) : CoreLibApi {
    val client = HttpClient()
    lateinit var postDataRegistrartion: HttpResponse
    lateinit var postDataUpdate: HttpResponse
    lateinit var resultRegistrartion : ResponseWS
    lateinit var resultUpdate : ResponseWS
    /**
     * Posts user data to the server.
     * @return A ResponseWS object containing the response from the server.
     */
    @OptIn(InternalAPI::class, DelicateCoroutinesApi::class)
    actual override suspend fun postUserData(data: RequestData): ResponseWS {
         val TAG = "Android Repository Register"
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
                //"check" to data.check,
                "check" to "944150675E23A25B1C6A4CDE5981EA22",
                "push_additional_params[${data.pushAdditionalParams.keys}]" to "${data.pushAdditionalParams.values}",
                "screen_dpi" to data.screenDpi
            )
            Log.w(TAG,"the data is :$formData" )
            Log.w(TAG,"the check is :${formData.filter { it.key == "check" }}" )
            val parameters = Parameters.build {
                formData.forEach { (key, value) ->
                    append(key, value)
                }
            }
            try {
                postDataRegistrartion = client.post(Constants.BASE_URL) {
                    body = FormDataContent(parameters)
                    contentType(ContentType.Application.FormUrlEncoded)
                }
            } catch (e: ClientRequestException) {
                Log.w(TAG,"Error :${e.response.status.description}" )
            } finally {
                client.close()
            }
            Log.w(TAG,"the result is :${postDataRegistrartion.bodyAsText()}" )
            val test = JSONObject(postDataRegistrartion.bodyAsText())

            val responseDescriptionResult = test.getJSONObject("response_description")
            responseDescriptionResult.keys().forEach { key ->
                val value = responseDescriptionResult.getString(key)
                Log.w(TAG,"the responseDescriptionResult is :${"$key: $value"}" )
            }


            // Extract response_code
            val responseCodeResult = test.getString("response_code")
            Log.w(TAG,"the responseCodeResult is :${responseCodeResult}" )

            // Extract mpDeviceIdentifier from response_data
            val responseData = test.getJSONObject("response_data")
            val mpDeviceIdentifier = responseData.getString("mpDeviceIdentifier")
            Log.w(TAG,"the mpDeviceIdentifier is :${mpDeviceIdentifier}" )

            val responseMessageResult = test.getJSONObject("response_message")
            Log.w(TAG,"the responseMessageResult is :${responseMessageResult}" )

            val responseCode: String? = responseCodeResult
            val responseDescriptionMap = mutableMapOf<String, String>()

            responseDescriptionResult.keys().forEach { key ->
                val value = responseDescriptionResult.getString(key)
                responseDescriptionMap[key] = value
                Log.w(TAG, "the responseDescriptionResult is: $key: $value")
            }
            val responseDataObject = ResponseData(mpDeviceIdentifier)
            GlobalScope.launch {
                DataManager(context).saveMpDeviceIdentifier("mpDeviceIdentifier",mpDeviceIdentifier)
            }
            resultRegistrartion = ResponseWS(
                responseCode,
                responseDescriptionMap,
                responseMessageResult,
                responseDataObject
            )
        }
        return resultRegistrartion
    }

    @SuppressLint("SuspiciousIndentation")
    @OptIn(InternalAPI::class)
    actual override suspend fun getUpdateDeviceInfo(
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
    ): ResponseWS {
        val TAG = "Android Repository Update"

        runBlocking {
            val formData = mapOf(
                "action" to action,
                "mp_device_identifier" to deviceIdentifier,
                "app_version" to appVersion,
                "os_version" to osVersion,
                "device_region" to deviceRegion,
                "device_timezone" to deviceTimezone,
                "device_identifier" to deviceIdentifier,
                "timestamp" to timestamp,
                //"check" to "944150675E23A25B1C6A4CDE5981EA22",
                "check" to check,
                "device_info" to deviceInfo,
                "push_additional_params" to additionalParams
            )
            Log.w(TAG,"the data is :$formData" )
            val parameters = Parameters.build {
                formData.forEach { (key, value) ->
                    (value as? String)?.let { append(key, it) }
                }
            }
            try {
                postDataUpdate = client.post(Constants.BASE_URL) {
                    body = FormDataContent(parameters)
                    contentType(ContentType.Application.FormUrlEncoded)
                }
            } catch (e: ClientRequestException) {
                println("Error: ${e.response.status.description}")
            } finally {
                client.close()
            }
            Log.w(TAG,"the result is :${postDataUpdate.bodyAsText()}" )
            val test = JSONObject(postDataUpdate.bodyAsText())

            val responseDescriptionResult = test.getJSONObject("response_description")
                        responseDescriptionResult.keys().forEach { key ->
                    val value = responseDescriptionResult.getString(key)
                    Log.w(TAG,"the responseDescriptionResult is :${"$key: $value"}" )
                }


                // Extract response_code
                val responseCodeResult = test.getString("response_code")
            Log.w(TAG,"the responseCodeResult is :${responseCodeResult}" )

            // Extract mpDeviceIdentifier from response_data
            val responseData = test.getJSONObject("response_data")
            //val mpDeviceIdentifier = responseData.getString("mpDeviceIdentifier")
            Log.w(TAG,"the mpDeviceIdentifier is :${responseData}" )

            val responseMessageResult = test.getJSONObject("response_message")
            Log.w(TAG,"the responseMessageResult is :${responseMessageResult}" )

            val responseCode: String? = responseCodeResult
            val responseDescriptionMap = mutableMapOf<String, String>()

            responseDescriptionResult.keys().forEach { key ->
                val value = responseDescriptionResult.getString(key)
                responseDescriptionMap[key] = value
                Log.w(TAG, "the responseDescriptionResult is: $key: $value")
            }

            val responseDataObject = ResponseData(null)
            resultUpdate = ResponseWS(
                responseCode,
                responseDescriptionMap,
                responseMessageResult,
                responseDataObject
            )
        }
        return resultUpdate
    }
}