package com.example.connect.main.ui.menu.info_pendidikan.pendidikan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListMyPendidikanBinding
import com.example.connect.main.ui.menu.info_pendidikan.MySubPendidikan

class Adapter(private val onClickListener: OnClickListener, private val onClickEditListener: OnClickEditListener) :
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

    class OnClickListener(val clickListener: (property: MySubPendidikan) -> Unit) {
        fun onClick(property: MySubPendidikan) = clickListener(property)
    }

    class OnClickEditListener(val clickListener: (property: MySubPendidikan) -> Unit) {
        fun onClick(property: MySubPendidikan) = clickListener(property)
    }

    class ViewHolder(private val binding: ItemListMyPendidikanBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val editButton = binding.imageButton3

        fun bind(property: MySubPendidikan) {
            binding.binding = property
            binding.executePendingBindings()
        }
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
            onClickEditListener.onClick(property)
        }

        holder.editButton.setOnClickListener {
            onClickListener.onClick(property)
        }

        holder.bind(property)
    }
}