package com.example.connect.presentation.main.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemProductKhususBinding
import com.example.connect.domain.entity.ProductEntity

class ProductAdapter(val onclickListener: OnclickListener) :
    ListAdapter<ProductEntity, ProductAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemProductKhususBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductEntity) {
            binding.productKhusus = data
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem == newItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductKhususBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val productItem = getItem(position)
        holder.bind(productItem)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(productItem)
        }
    }

    class OnclickListener(
        val clickListener: (entity: ProductEntity) ->
        Unit
    ) {
        fun onClick(entity: ProductEntity) = clickListener(entity)
    }
}