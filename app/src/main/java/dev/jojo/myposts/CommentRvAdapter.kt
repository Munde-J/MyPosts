package dev.jojo.myposts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jojo.myposts.databinding.CommentListItemBinding

class CommentRvAdapter(var commentList:List<Comment>):RecyclerView.Adapter<CommentViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
       var binding = CommentListItemBinding
           .inflate(LayoutInflater.from(parent.context), parent,false)
        return  CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var currentComment = commentList.get(position)
        with(holder.binding){
            tvPostIdContent.text = currentComment.postId.toString()
            tvIdContent.text = currentComment.id.toString()
            tvNameContent.text = currentComment.name.toString()
            tvEmailContent.text = currentComment.email.toString()
            tvBodyContent.text = currentComment.body.toString()

        }

    }

    override fun getItemCount(): Int {
        return commentList.size

   }
}
class CommentViewHolder(var binding: CommentListItemBinding):RecyclerView.ViewHolder(binding.root)