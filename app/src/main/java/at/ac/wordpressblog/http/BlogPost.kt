package at.ac.wordpressblog.http

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

@RequiresApi(Build.VERSION_CODES.O)
class BlogPost(
    val id: Int,
    val date: String,
    val title: Rendered,
    val content: Rendered,
    val excerpt: Rendered,
    @Json(name="_embedded")
    val embedded: Embedded
) {
    fun getDate(): LocalDateTime {
        return LocalDateTime.parse(this.date)
    }
}
