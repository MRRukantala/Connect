package com.example.connect.main.ui.dashboard.store.umum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemProductUmumBinding
import com.example.connect.main.ui.dashboard.modelproductumum.ProductUmum

class ProductUmumAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProductUmum, ProductUmumAdapter.ProductUmumViewHolder>(DiffCallback) {

    class ProductUmumViewHolder(private val binding: ItemProductUmumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productUmum: ProductUmum) {
            binding.productUmum = productUmum
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (productUmumProperty: ProductUmum) -> Unit) {
        fun onClick(productUmumPropery: ProductUmum) = clickListener(productUmumPropery)
    }

    object DiffCallback : DiffUtil.ItemCallback<ProductUmum>() {
        override fun areItemsTheSame(oldItem: ProductUmum, newItem: ProductUmum): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductUmum, newItem: ProductUmum): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductUmumViewHolder {
        return ProductUmumViewHolder(
            ItemProductUmumBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ProductUmumViewHolder, position: Int) {
        val productUmumProperty = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(productUmumProperty)
        }

        holder.bind((productUmumProperty))
    }
}