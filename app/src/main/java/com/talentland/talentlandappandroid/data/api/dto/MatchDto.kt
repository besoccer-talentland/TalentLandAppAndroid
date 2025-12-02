package com.talentland.talentlandappandroid.data.api.dto

import com.google.gson.annotations.SerializedName

data class MatchDto(
    @SerializedName("localName")
    val localName: String,
    @SerializedName("visitorName")
    val visitorName: String,
    @SerializedName("localShield")
    val localShield: String?,
    @SerializedName("visitorShield")
    val visitorShield: String?,
    @SerializedName("score")
    val score: String?,
    @SerializedName("liveMinute")
    val liveMinute: Int?
)


