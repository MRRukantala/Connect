package com.example.connect.main.ui.product.tabLayout.productumum

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemProductUmumBinding
import com.example.connect.main.ui.product.model.ProductModel

class ProductUmumAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProductModel, ProductUmumAdapter.ProductUmumViewHolder>(DiffCallback) {

    class ProductUmumViewHolder(private val binding: ItemProductUmumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(productModel: ProductModel) {
            binding.productModel = productModel
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (productModelProperty: ProductModel) -> Unit) {
        fun onClick(productModelPropery: ProductModel) = clickListener(productModelPropery)
    }

    object DiffCallback : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
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

        holder.bind(productUmumProperty)
    }
}