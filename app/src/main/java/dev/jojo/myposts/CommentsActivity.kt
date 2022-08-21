package dev.jojo.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.jojo.myposts.databinding.ActivityCommentsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsActivity : AppCompatActivity() {
    var postId = 0
    lateinit var binding:ActivityCommentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        obtainPostId()
        fetchPostById()
        obtainComments()
    }

    fun obtainPostId(){
        postId = intent.extras?.getInt("POST_ID") ?: 0
    }

    fun fetchPostById(){
        val apiClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.getPostsById(postId)
        request.enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful){
                    var Post = response.body()
                    binding.tvPostTitle.text = Post?.title
                    binding.tvPostBody.text = Post?.body
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
              Toast.makeText(baseContext,t.message, Toast.LENGTH_LONG)
            }

        })
    }

    fun setuptoolbar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }
    fun obtainComments(){
        val getClient = ApiClient.buildApiClient(ApiInterface::class.java)
        val request = getClient.obtainComments()

        request.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful){
                    val comments = response.body()
                    if (comments!=null){
                        displayComments(comments)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
            }
    })
}
    fun displayComments(commentList: List<Comment>){
        binding.rvComments.layoutManager = LinearLayoutManager(this)
        val commentAdapter = CommentRvAdapter(commentList)
        binding.rvComments.adapter = commentAdapter

    }
}
