package at.ac.wordpressblog

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.ac.wordpressblog.http.BlogPost
import at.ac.wordpressblog.http.WordpressApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
class BlogPostViewModel: ViewModel(){

    private val _posts = MutableLiveData<List<BlogPost>>()
    public val posts: LiveData<List<BlogPost>> get() = _posts
    internal val LOG_TAG = MainActivity::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    //2021-02-01
    @RequiresApi(Build.VERSION_CODES.O)
    //&after={DAY_LIMIT}T00:00:00
    private val day_limit = LocalDate.now().minusDays(21).format(formatter) + "T00:00:00"

    init {
        getBlogPosts()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getBlogPosts(){
        viewModelScope.launch {
            try {
                _posts.value = WordpressApi.retrofitService.getBlogPosts(day_limit)
            }
            catch (e: Exception){
                Log.d(LOG_TAG, e.toString())
            }
        }
    }
}