package at.ac.wordpressblog.http

import com.squareup.moshi.Json

data class SourceUrl(
    @Json(name="source_url")
    val sourceUrl: String
)

data class Rendered(
    val rendered: String
)

data class Embedded(
    @Json(name="wp:featuredmedia")
    val media: List<SourceUrl>
)

data class BlogPost(
    val id: Int,
    val date: String,
    val title: Rendered,
    val content: Rendered,
    @Json(name="_embedded")
    val embedded: Embedded
)