package com.jorge.core.data.response

data class DataWrapperResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val dataContainerResponse: DataContainerResponse,
    val etag: String,
    val status: String
)