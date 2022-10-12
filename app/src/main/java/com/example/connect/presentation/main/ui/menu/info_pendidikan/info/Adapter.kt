package com.example.connect.presentation.main.ui.menu.info_pendidikan.info

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListMyPendidikanBinding
import com.example.connect.presentation.main.ui.menu.info_pendidikan.MySubPendidikan

class Adapter(private val onClickListener: OnClickListener) :
    ListAdapter<MySubPendidikan, Adapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<MySubPendidikan>() {
        override fun areItemsTheSame(oldItem: MySubPendidikan, newItem: MySubPendidikan): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: MySubPendidikan,
            newItem: MySubPendidikan
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ViewHolder(private var binding: ItemListMyPendidikanBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(mySubPendidikan: MySubPendidikan) {
            binding.binding = mySubPendidikan
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (MySubPendidikan: MySubPendidikan) -> Unit) {
        fun onClick(mySubPendidikan: MySubPendidikan) = clickListener(mySubPendidikan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListMyPendidikanBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(property)
        }

        holder.bind(property)
    }

}