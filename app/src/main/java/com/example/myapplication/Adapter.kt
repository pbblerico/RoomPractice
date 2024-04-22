package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemViewBinding
import com.example.myapplication.db.StringEntity

class Adapter(
    private val delete: ((model: StringEntity) -> Unit)
): ListAdapter<StringEntity, Adapter.StringViewHolder>(DIFF_CALLBACK) {

    class StringViewHolder(
        private val binding: ItemViewBinding,
        private val delete: ((model: StringEntity) -> Unit)
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: StringEntity){
            binding.text.text = item.str

            binding.delete.setOnClickListener {
                delete.invoke(item)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<StringEntity>() {
            override fun areItemsTheSame(oldItem: StringEntity, newItem: StringEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: StringEntity, newItem: StringEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(layoutInflater, parent, false)
        return StringViewHolder(binding, delete)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}