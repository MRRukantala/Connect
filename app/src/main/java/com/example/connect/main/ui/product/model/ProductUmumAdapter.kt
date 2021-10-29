//package com.example.connect.main.ui.dashboard.modelproductumum
//
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.connect.databinding.ItemProductUmumBinding
//
//class ProductUmumAdapter() :
//    ListAdapter<ProductModel, ProductUmumAdapter.ProductViewHolder>(DiffCallback) {
//    class ProductViewHolder(private var binding: ItemProductUmumBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//            fun bind(productUmum: ProductModel){
//
//            }
//    }
//
//    object DiffCallback : DiffUtil.ItemCallback<ProductModel>() {
//        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
//            return oldItem.id == newItem.id
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductUmumAdapter {
//        return
//    }
//
//
//    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
//
//    }
//
//}