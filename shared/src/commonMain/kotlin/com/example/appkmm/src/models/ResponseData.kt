package com.mobelite.corelibkmm.src.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseData(
    /**This is for device registration & update device info**/
    @SerialName("mpDeviceIdentifier")
    var mpDeviceIdentifier: String?,

    /**This is for check for update**/
    @SerialName("has_update")
    var hasUpdate: String?,

    @SerialName("last_version")
    var lastVersion: String?,

    @SerialName("last_version_url")
    var lastVersionUrl: String?,

    @SerialName("last_version_will_block")
    var lastVersionWillBlock: String?,

    @SerialName("version_title")
    var versionTitle: String?,

    @SerialName("version_description")
    var versionDescription: String?,

    @SerialName("accept_btn_text")
    var acceptBtnText: String?,

    @SerialName("cancel_btn_text")
    var cancelBtnText: String?,

    @SerialName("display_evry")
    var displayEvry: String?,

    @SerialName("display_launch")
    var displayLaunch: String?,

    /**This is for welcome message check**/
    @SerialName("has_wm")
    var hasWm: String?,

    @SerialName("wm_title")
    var wmTitle: String?,

    @SerialName("wm_message")
    var wmMessage: String?,

    @SerialName("dismiss_btn_text")
    var dismissBtnText: String?,

    @SerialName("wm_display_every")
    var wmDisplayEvery: String?,

    @SerialName("wm_display_launch")
    var wmDisplayLaunch: String?,

    @SerialName("wm_id")
    var wmId: String?,

    /**This is for opened notification**/
    @SerialName("notification_status_data")
    var notificationStatusData: Map<String?, String?>?,

    /**This is for GEt URL**/
    @SerialName("url")
    var url: String?
)