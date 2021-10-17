//package com.example.connect.main.ui.dashboard.modelproductumum
//
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.connect.databinding.ItemProductUmumBinding
//
//class ProductUmumAdapter() :
//    ListAdapter<ProductUmum, ProductUmumAdapter.ProductViewHolder>(DiffCallback) {
//    class ProductViewHolder(private var binding: ItemProductUmumBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//            fun bind(productUmum: ProductUmum){
//
//            }
//    }
//
//    object DiffCallback : DiffUtil.ItemCallback<ProductUmum>() {
//        override fun areItemsTheSame(oldItem: ProductUmum, newItem: ProductUmum): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: ProductUmum, newItem: ProductUmum): Boolean {
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