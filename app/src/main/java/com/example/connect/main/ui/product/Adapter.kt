package com.example.connect.main.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemProductKhususBinding
import com.example.connect.main.ui.product.model.ProductModel

class Adapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProductModel, Adapter.ProductViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ProductViewHolder(private var binding: ItemProductKhususBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(productKhusus : ProductModel) {
            binding.productKhusus = productKhusus
            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (productProperty : ProductModel) -> Unit){
        fun onClick(productProperty: ProductModel) = clickListener(productProperty)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductKhususBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productProperty = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(productProperty)
        }

        holder.bind(productProperty)
    }
}