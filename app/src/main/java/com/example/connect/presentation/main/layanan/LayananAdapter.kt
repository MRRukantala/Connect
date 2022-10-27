package com.example.connect.presentation.main.layanan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListServiceAdminBinding
import com.example.connect.domain.entity.LayananEntity

class LayananAdapter(val onclickListener: OnclickListener) :
    ListAdapter<LayananEntity, LayananAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemListServiceAdminBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LayananEntity) {
            binding.data = data
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<LayananEntity>() {
        override fun areItemsTheSame(oldItem: LayananEntity, newItem: LayananEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: LayananEntity, newItem: LayananEntity) =
            oldItem.id == newItem.id
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListServiceAdminBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layananItem = getItem(position)
        holder.bind(layananItem)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(layananItem)
        }
    }

    class OnclickListener(
        val clickListener: (entity: LayananEntity) ->
        Unit
    ) {
        fun onClick(entity: LayananEntity) = clickListener(entity)
    }
}