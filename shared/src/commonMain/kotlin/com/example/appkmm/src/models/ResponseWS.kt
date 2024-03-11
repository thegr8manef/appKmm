package com.mobelite.corelibkmm.src.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ResponseWS(

    @SerialName("response_code")
    var responseCode: String?,

    @SerialName("response_description")
    var responseDescription: Map<String, String>?,

    @Contextual
    @SerialName("response_message")
    var responseMessage: Any?,

    @SerialName("response_data")
    var responseData: ResponseData?
)