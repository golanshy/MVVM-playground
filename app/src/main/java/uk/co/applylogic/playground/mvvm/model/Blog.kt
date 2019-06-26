package uk.co.applylogic.playground.mvvm.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class Blog {
    @Json(name = "author")
    var author: String? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "link")
    var link: String? = null
    @Json(name = "pubDate")
    var pubDate: String? = null
    @Json(name = "thumbnail")
    var thumbnail: String? = null
    @Json(name = "title")
    var title: String? = null
}