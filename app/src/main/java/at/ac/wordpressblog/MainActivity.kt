package at.ac.wordpressblog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import at.ac.wordpressblog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: BlogPostViewModel by viewModels()
    internal val LOG_TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this, R.layout.activity_main
        )

        viewModel.posts.observeForever{
            print(it)
            Log.d(LOG_TAG, "onSaveInstance(), counter: {${it}}")
            binding.count = it.count().toString()
        }
    }



}