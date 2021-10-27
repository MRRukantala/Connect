package com.example.connect.main.ui.dashboard.store

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemProductKhususBinding
import com.example.connect.main.ui.dashboard.modelproductumum.ProductUmum

class ProductAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProductUmum, ProductAdapter.ProductViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<ProductUmum>() {
        override fun areItemsTheSame(oldItem: ProductUmum, newItem: ProductUmum): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductUmum, newItem: ProductUmum): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class ProductViewHolder(private var binding: ItemProductKhususBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(productKhusus : ProductUmum) {
            binding.productKhusus = productKhusus
            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (productProperty : ProductUmum) -> Unit){
        fun onClick(productProperty: ProductUmum) = clickListener(productProperty)

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