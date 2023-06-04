package com.yosua.yourstory.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yosua.yourstory.databinding.ItemStoryBinding
import com.yosua.yourstory.domain.model.Story
import com.yosua.yourstory.utils.Constants.STORAGE

/**
 * Created by Yosua on 03/06/2023
 */
class StoryAdapter(private val listStory: ArrayList<Story>) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(var binding: ItemStoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val data = listStory[position]

        holder.binding.apply {
            tvAuthor.text = data.name.removeSurrounding("\"")
            tvDescription.text = data.description

            if (data.image!!.isNotEmpty()) {
                Glide
                    .with(root)
                    .load(STORAGE + data.image)
                    .into(ivPostedStory)
            } else {
                ivPostedStory.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int = listStory.size
}