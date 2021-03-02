package at.ac.wordpressblog

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import at.ac.wordpressblog.http.BlogPost
import at.ac.wordpressblog.http.SourceUrl
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.time.format.DateTimeFormatter

//todo: creating adapter class
class BlogPostAdapter(val blogPosts: List<BlogPost>): RecyclerView.Adapter<BlogPostAdapter.ViewHolder>() {
    class ViewHolder(val card: View): RecyclerView.ViewHolder(card) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.blogpost_card, parent, false)

        var viewHolder = ViewHolder(view)

        return viewHolder;
    }

    override fun getItemCount(): Int = blogPosts.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val blogPost = blogPosts[position]

        with(holder.card) {
            findViewById<TextView>(R.id.tv_title).text = "${blogPost.title.rendered}"
            findViewById<TextView>(R.id.tv_datum).text = "${blogPost.getDate().format(formatter)}"

            bindImage(findViewById<ImageView>(R.id.iv_card), blogPost.embedded.media[0].sourceUrl)
        }
    }

    fun bindImage(imgView: ImageView, imgUrl: String?) {
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Glide.with(imgView.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(imgView)
        }
    }

}

//todo: creating blog post card for recycler view
//todo: connecting recycler view with the created card
//todo: bind image
//todo: creating a loader using Glide
//todo: creating fragments for detail view
//todo: navigation
//todo: reloader