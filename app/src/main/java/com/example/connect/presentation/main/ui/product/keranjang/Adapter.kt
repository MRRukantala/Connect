package com.example.connect.presentation.main.ui.product.keranjang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.data.database.SaveProductData
import com.example.connect.databinding.ItemListSaveProductBinding

class Adapter(private val onClickListener: OnClickListener,
              private val onClickDelete: OnClickListenerDelete,
              private val onClickCall: OnClickCall
) :
ListAdapter<SaveProductData, Adapter.ViewHolder>(DiffCallback){

    object DiffCallback : DiffUtil.ItemCallback<SaveProductData>(){
        override fun areItemsTheSame(oldItem: SaveProductData, newItem: SaveProductData): Boolean {
            return oldItem.idSaveProductData == newItem.idSaveProductData
        }

        override fun areContentsTheSame(
            oldItem: SaveProductData,
            newItem: SaveProductData
        ): Boolean {
            return oldItem == newItem
        }
    }


    class OnClickListener(val clickListener: (saveProductId: Long) -> Unit) {
        fun onClick(SaveProductData: SaveProductData) = clickListener(SaveProductData.idSaveProductData)
    }

    class OnClickListenerDelete(val clickListener: (saveProductId: Long) -> Unit) {
        fun onClick(SaveProductData: SaveProductData) = clickListener(SaveProductData.idSaveProductData)
    }

    class OnClickCall(val clickListener: (saveProductData: SaveProductData) -> Unit) {
        fun onClick(SaveProductData: SaveProductData) = clickListener(SaveProductData)
    }


    class ViewHolder(private val binding : ItemListSaveProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val buttonDelete  = binding.imageButton3

        val buttonCall = binding.call

        fun bind(SaveProductData: SaveProductData){
//            binding.viewModel = SaveProductData
            binding.executePendingBindings()
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListSaveProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val property = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(property)
        }

        holder.buttonDelete.setOnClickListener {
            onClickDelete.onClick(property)
        }

        holder.buttonCall.setOnClickListener {
            onClickCall.onClick(property)
        }

        holder.bind(property)
    }
}