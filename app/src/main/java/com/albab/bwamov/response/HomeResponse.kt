package com.albab.bwamov.response

import com.google.gson.annotations.SerializedName

data class HomeResponse (

    @SerializedName("success")
    var success: Boolean,

    @SerializedName("message")
    var message: String
)