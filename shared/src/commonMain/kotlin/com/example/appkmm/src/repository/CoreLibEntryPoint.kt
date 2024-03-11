package com.example.appkmm.src.repository

import com.example.appkmm.src.models.RequestData
import com.mobelite.corelibkmm.src.models.ResponseWS


object CoreLibEntryPoint {
    private fun initLibrary(): UserRepository {
        return UserRepository()
    }


//    suspend fun saveUserData(context : Any?) {
//        return initLibrary().saveData(context)
//    }

    suspend fun readUserData(context : Any?) : RequestData? {
        return initLibrary().readData(context)
    }

    suspend fun postUserData(context : Any?): Any {
        return if (initLibrary().statusConnection(context)) {
            if (initLibrary().readData(context) == null) {
                initLibrary().postData()
            }
                 initLibrary().postData()
            } else {
               return ResponseWS(null,null,null,null)
        }
        // } else {

        }
    }