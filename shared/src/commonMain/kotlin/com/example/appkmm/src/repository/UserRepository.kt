package com.example.appkmm.src.repository

import Utils
import com.example.appkmm.src.data.local.manager.DataManager
import com.example.appkmm.src.data.remote.repository.Repository
import com.example.appkmm.src.managers.DeviceInfo
import com.example.appkmm.src.managers.getDeviceInfo
import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS


class UserRepository() {
    private val deviceModel: DeviceInfo = getDeviceInfo()
    val data : RequestData = RequestData(
        action= "mpRegisterDevice",
        deviceIdentifier ="0DEA426A-C06E-42F9-A1B4-F12308089455",
        appIdentifier = "com.elsevier-masson.gt11",
        appVersion ="1.3.1",
        osVersion = deviceModel.osVersion,
        os= deviceModel.osName,
        deviceModel =deviceModel.deviceModel,
        deviceManufacture =deviceModel.deviceManufacturer,
        deviceRegion = deviceModel.deviceRegion,
        deviceTimezone = deviceModel.timeZone,
        screenResolution = "1242*2688",
        timestamp=deviceModel.timestamp.toString(),
        check= "944150675E23A25B1C6A4CDE5981EA22",
        pushAdditionalParams = mapOf("GTUser" to "Not_Connected"),
        screenDpi = deviceModel.screenDpi
    )

    suspend fun postData(): ResponseWS {
        return Repository().postUserData(data)
    }

    suspend fun saveData(context : Any?){
        return DataManager(context).saveData("test",data)
    }
    suspend fun readData(context : Any?): RequestData? {
        return DataManager(context).readData("test")
    }
    fun statusConnection(context: Any?) : Boolean {
        return Utils(context).isConnected()
    }

}