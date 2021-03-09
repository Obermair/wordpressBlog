package at.ac.wordpressblog.http

import android.os.Build
import androidx.annotation.RequiresApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.LocalDate
import java.time.format.DateTimeFormatter

private const val BASE_URL = "https://www.htl-leonding.at/"
//private const val BASE_URL = "http://172.17.213.112:8080/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WordpressApiService {
    @GET("wp-json/?rest_route=/wp/v2/posts&_embed=wp:featuredmedia")
    suspend fun getBlogPosts(@Query(value="after", encoded = true) date: String): List<BlogPost>
}

object WordpressApi {
    val retrofitService: WordpressApiService by lazy{
        retrofit.create(WordpressApiService::class.java)
    }
}
