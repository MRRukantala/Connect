package com.example.connect.presentation.main.ui.product.tabLayout.myproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListMyProductBinding
import com.example.connect.presentation.main.ui.product.model.ProductModel

class Adapter(private val onClickListener: OnClickListener) :
    ListAdapter<ProductModel, Adapter.MyProdukViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class MyProdukViewHolder(private val binding: ItemListMyProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productModel: ProductModel) {
            binding.productModel = productModel
            binding.executePendingBindings()
        }

    }

    class OnClickListener(val clickListener: (productModelProperty: ProductModel) -> Unit) {
        fun onClick(productModel: ProductModel) = clickListener(productModel)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyProdukViewHolder {
        return MyProdukViewHolder(
            ItemListMyProductBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: MyProdukViewHolder, position: Int) {
        val property = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(property)
        }

        holder.bind(property)
    }
}