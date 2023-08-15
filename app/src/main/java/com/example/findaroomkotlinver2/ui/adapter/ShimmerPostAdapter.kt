package com.example.findaroomkotlinver2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.findaroomkotlinver2.R
import com.facebook.shimmer.ShimmerFrameLayout

class ShimmerPostAdapter(private val itemCount: Int) :
    RecyclerView.Adapter<ShimmerPostAdapter.ShimmerViewHolder>() {
    inner class ShimmerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShimmerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.layout_shimmer_post_item, parent, false)
        return ShimmerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShimmerViewHolder, position: Int) {
        val shimmerLayout = holder.itemView.findViewById<ShimmerFrameLayout>(R.id.shimmerLayout)
        shimmerLayout.startShimmer()
    }

    override fun getItemCount(): Int = itemCount
}