package at.ac.wordpressblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import at.ac.wordpressblog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: BlogPostViewModel by viewModels()
    internal val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        binding.rvBlogPosts.layoutManager = LinearLayoutManager(this)
        binding.rvBlogPosts.hasFixedSize()

        viewModel.posts.observeForever {
            binding.rvBlogPosts.adapter = BlogPostAdapter(it)
        }

    }



}