package com.example.marvelapp.framework.response

import com.google.gson.annotations.SerializedName

data class ThumbnailRespose(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)