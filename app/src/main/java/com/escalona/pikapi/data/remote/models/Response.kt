package com.escalona.pikapi.data.remote.models

import com.squareup.moshi.Json

data class Response(
    @Json(name = "count") val count: Int,
    @Json(name = "next") val next: String?,
    @Json(name = "results") val results: List<Result>,
)

data class Result(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)

fun Result.getResourceId() = this.url
    .split("/")
    .last { it.isNotEmpty() }
    .toInt()

