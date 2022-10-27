package com.example.connect.presentation.main.product.tabLayout.myproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListMyProductBinding
import com.example.connect.domain.entity.ProductEntity

class MyProductAdapter(val onclickListener: OnclickListener) :
    ListAdapter<ProductEntity, MyProductAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemListMyProductBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ProductEntity) {
            binding.productModel = data
            binding.executePendingBindings()
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<ProductEntity>() {
        override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity) =
            oldItem.id == newItem.id
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListMyProductBinding.inflate(
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