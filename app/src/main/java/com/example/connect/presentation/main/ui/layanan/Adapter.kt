package com.example.connect.presentation.main.ui.layanan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListServiceAdminBinding

class Adapter(private val onClickListener: OnClickistener) :
    ListAdapter<DataLayanan, Adapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<DataLayanan>() {
        override fun areItemsTheSame(oldItem: DataLayanan, newItem: DataLayanan): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataLayanan, newItem: DataLayanan): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ViewHolder(private var binding: ItemListServiceAdminBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(layanan: DataLayanan) {
            binding.viewModel = layanan
            binding.executePendingBindings()
        }
    }

    class OnClickistener(val clickListener: (layanan: DataLayanan) -> Unit) {
        fun onClick(layanan: DataLayanan) = clickListener(layanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListServiceAdminBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layanan = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(layanan)
        }

        holder.bind(layanan)
    }
}