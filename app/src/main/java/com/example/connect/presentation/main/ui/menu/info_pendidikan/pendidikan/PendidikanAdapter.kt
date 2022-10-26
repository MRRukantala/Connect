package com.example.connect.presentation.main.ui.menu.info_pendidikan.pendidikan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListMyPendidikanBinding
import com.example.connect.domain.entity.PendidikanEntity

class PendidikanAdapter(val onclickListener: OnclickListener) :
    ListAdapter<PendidikanEntity, PendidikanAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemListMyPendidikanBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PendidikanEntity) {
            binding.binding = data
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PendidikanEntity>() {
        override fun areItemsTheSame(oldItem: PendidikanEntity, newItem: PendidikanEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PendidikanEntity, newItem: PendidikanEntity) =
            oldItem.id == newItem.id
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListMyPendidikanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pendidikanItem = getItem(position)
        holder.bind(pendidikanItem)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(pendidikanItem)
        }
    }

    class OnclickListener(
        val clickListener: (entity: PendidikanEntity) ->
        Unit
    ) {
        fun onClick(entity: PendidikanEntity) = clickListener(entity)
    }
}