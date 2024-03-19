package com.example.appkmm.src.repository


import Utils
import com.example.appkmm.src.data.local.manager.DataManager
import com.example.appkmm.src.data.remote.repository.Repository
import com.example.appkmm.src.managers.DeviceInfo
import com.example.appkmm.src.managers.getDeviceInfo
import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS
import com.mobelite.corelibkmm.src.utils.Constants


class UserRepository(private val context: Any?) {
    private val deviceModel: DeviceInfo = getDeviceInfo()
     suspend fun getCheck() : String {
        return Utils(context).stringToMD5(deviceModel.deviceIdentifierForVendor + "||" + deviceModel.appIdentifier + "||" + deviceModel.timestamp + "||" + readCoreLibId())
    }
    suspend fun  data (): RequestData {
        return RequestData(
            action= Constants.ACTION_REGISTER,
            deviceIdentifier =deviceModel.deviceIdentifierForVendor,
            appIdentifier = deviceModel.appIdentifier,
            appVersion =deviceModel.appVersion,
            osVersion = deviceModel.osVersion,
            os= deviceModel.osName,
            deviceModel =deviceModel.deviceModel,
            deviceManufacture =deviceModel.deviceManufacturer,
            deviceRegion = deviceModel.deviceRegion,
            deviceTimezone = deviceModel.timeZone,
            screenResolution = "1242*2688",
            timestamp=deviceModel.timestamp.toString(),
            //check= "944150675E23A25B1C6A4CDE5981EA22",
            check = getCheck(),
            pushAdditionalParams = mapOf("GTUser" to "Not_Connected"),
            screenDpi = deviceModel.screenDpi
        )
    }

    suspend fun postData(): ResponseWS {
        return Repository(context).postUserData(data())
    }
    suspend fun updateData(): ResponseWS {
        return Repository(context).getUpdateDeviceInfo(
            action = Constants.ACTION_UPDATE,
            mpDeviceIdentifier = readMpDeviceIdentifier(),
            appVersion = deviceModel.appVersion,
            osVersion = deviceModel.osVersion,
            deviceRegion = deviceModel.deviceRegion,
            deviceTimezone = deviceModel.timeZone,
            deviceIdentifier = deviceModel.deviceIdentifierForVendor,
            timestamp = deviceModel.timestamp.toString(),
            check = getCheck(),
            deviceInfo = mapOf("param1" to "param2"),
            additionalParams = mapOf("GTUser" to "Not_Connected")
        )
    }

    suspend fun saveData(){
        return DataManager(context).saveData("Data",data())
    }
    suspend fun readData(): RequestData? {
        return DataManager(context).readData("Data")
    }
    fun statusConnection() : Boolean {
        return Utils(context).isConnected()
    }
    suspend fun saveCoreLibId(coreLibId : String){
        return DataManager(context).saveCoreLibId("CoreLibId",coreLibId)
    }
    suspend fun readCoreLibId(): String? {
        return DataManager(context).readCoreLibId("CoreLibId")
    }
    suspend fun saveBaseUrl(baseUrl : String){
        return DataManager(context).saveBaseUrl("BaseUrl",baseUrl)
    }
    suspend fun readBaseUrl(): String? {
        return DataManager(context).readBaseUrl("BaseUrl")
    }

    suspend fun saveMpDeviceIdentifier(mpDeviceIdentifier : String){
        return DataManager(context).saveMpDeviceIdentifier("MpDeviceIdentifier",mpDeviceIdentifier)
    }
    suspend fun readMpDeviceIdentifier(): String? {
        return DataManager(context).readMpDeviceIdentifier("MpDeviceIdentifier")
    }

}