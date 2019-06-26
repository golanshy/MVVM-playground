package uk.co.applylogic.playground.mvvm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BlogWrapper {
    @Json(name = "data")
    var blog: MutableList<Blog>? = null
    @Json(name = "error")
    var error: Boolean? = null
    @Json(name = "message")
    var message: String? = null
    @Json(name = "status")
    var status: String? = null
}