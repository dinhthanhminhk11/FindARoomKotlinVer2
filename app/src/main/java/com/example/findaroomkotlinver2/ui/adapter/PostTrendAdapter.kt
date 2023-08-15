package com.example.findaroomkotlinver2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.findaroomkotlinver2.R
import com.example.findaroomkotlinver2.data.model.post.Post
import com.example.findaroomkotlinver2.databinding.ItemPostTrendBinding

class PostTrendAdapter :
    ListAdapter<Post, PostTrendAdapter.PostTrendViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostTrendViewHolder {
        val binding =
            ItemPostTrendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostTrendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostTrendViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    class PostTrendViewHolder(private val binding: ItemPostTrendBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)

            Glide.with(binding.root.context)
                .load(post.images[0])
                .apply(options)
                .into(binding.image)
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem._id == newItem._id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}