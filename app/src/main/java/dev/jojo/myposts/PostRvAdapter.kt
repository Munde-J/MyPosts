package dev.jojo.myposts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jojo.myposts.databinding.PostListItemBinding
import java.security.AccessControlContext

class PostRvAdapter ( var postList: List<Post>):RecyclerView.Adapter<PostViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        var binding = PostListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        var currentPost = postList.get(position)
        holder.binding.tvUserId.text= currentPost.userId.toString()
        holder.binding.tvId.text=currentPost.id.toString()
        holder.binding.tvTitle.text=currentPost.title.toString()
        holder.binding.tvBody.text=currentPost.body

            val context = holder.itemView.context
            holder.binding.cvPost.setOnClickListener{
                val intent = Intent(context,CommentsActivity::class.java)
                intent.putExtra("POST_ID",currentPost.id)//to put data
                context.startActivity(intent)
            }
        }


    override fun getItemCount(): Int {
        return postList.size

    }
}
class PostViewHolder(var binding:PostListItemBinding):RecyclerView.ViewHolder(binding.root)

