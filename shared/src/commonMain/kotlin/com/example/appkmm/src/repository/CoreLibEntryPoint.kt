package com.example.appkmm.src.repository

import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext


object CoreLibEntryPoint {
    private fun initLibrary(context: Any?): UserRepository {
        return UserRepository(context)
    }
     suspend fun corelibInit (env : String, coreLibId : String, context : Any?) : ResponseWS {
        val baseUrl = if (env == "prod") {
            "https://ws.mobeliteplus.fr/"
        } else {
            "http://ws.staging.mobeliteplus.com/"
        }
        initLibrary(context).saveCoreLibId(coreLibId)
        initLibrary(context).saveBaseUrl(baseUrl)
        //postUserData(context)
       return  postUserData(context)
    }
    suspend fun post(context: Any?) : ResponseWS {
       return initLibrary(context).postData()
    }
    suspend fun update(context: Any?) : ResponseWS {
        return initLibrary(context).updateData()
    }
    private suspend fun saveUserData(context : Any?) {
        return initLibrary(context).saveData()
    }

    private suspend fun readUserData(context: Any?): RequestData? {
        return initLibrary(context).readData()
    }
     suspend fun readCorelibId(context: Any?): String? {
        return initLibrary(context).readCoreLibId()
    }

    suspend fun postUserData(context: Any?): ResponseWS {
        return withContext(Dispatchers.IO) {
            val localData = initLibrary(context).readData()
            val newData = initLibrary(context).data()

            if (initLibrary(context).statusConnection()) {
                if (localData == null) {
                    val response = initLibrary(context).postData()
                    if (response.responseCode.equals("200")) {
                        initLibrary(context).saveData()
                        return@withContext ResponseWS(response.responseCode.toString(),response.responseDescription,response.responseMessage,response.responseData)
                    } else {
                        return@withContext ResponseWS("error", null, null, null)
                    }
                } else if (localData.deviceIdentifier == newData.deviceIdentifier && localData.appIdentifier == newData.appIdentifier && localData.check == newData.check && localData.deviceModel == newData.deviceModel && localData.appVersion == newData.appVersion && localData.deviceManufacture== newData.deviceManufacture && localData.deviceRegion == newData.deviceRegion && localData.deviceTimezone == newData.deviceTimezone && localData.osVersion == newData.osVersion && localData.os == newData.os && localData.screenResolution == newData.screenResolution && localData.screenDpi == newData.screenDpi ) {
                        initLibrary(context).saveData()
                        return@withContext ResponseWS("200",null,"the data is stored successfully",null)
                } else {
                    val update = initLibrary(context).updateData()
                    return@withContext ResponseWS(update.responseCode.toString(),update.responseDescription,update.responseMessage,update.responseData)

                }
            } else {
                return@withContext ResponseWS("500", null, "please check your internet connection", null)
            }
        }
    }
}
