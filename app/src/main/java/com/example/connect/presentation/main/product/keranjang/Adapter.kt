package com.example.connect.presentation.main.product.keranjang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.data.database.SaveProductDataEntity
import com.example.connect.databinding.ItemListSaveProductBinding
import com.example.connect.utilites.rupiah

class Adapter(
    private val onClickListener: OnClickListener,
    private val onClickDelete: OnClickListenerDelete,
    private val onClickCall: OnClickCall
) :
    ListAdapter<SaveProductDataEntity, Adapter.ViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<SaveProductDataEntity>() {
        override fun areItemsTheSame(
            oldItem: SaveProductDataEntity,
            newItem: SaveProductDataEntity
        ): Boolean {
            return oldItem.idSaveProductData == newItem.idSaveProductData
        }

        override fun areContentsTheSame(
            oldItem: SaveProductDataEntity,
            newItem: SaveProductDataEntity
        ): Boolean {
            return oldItem == newItem
        }
    }


    class OnClickListener(val clickListener: (saveProductId: SaveProductDataEntity) -> Unit) {
        fun onClick(SaveProductDataEntity: SaveProductDataEntity) =
            clickListener(SaveProductDataEntity)
    }

    class OnClickListenerDelete(val clickListener: (saveProductId: Long) -> Unit) {
        fun onClick(SaveProductDataEntity: SaveProductDataEntity) =
            clickListener(SaveProductDataEntity.idSaveProductData)
    }

    class OnClickCall(val clickListener: (saveProductDataEntity: SaveProductDataEntity) -> Unit) {
        fun onClick(SaveProductDataEntity: SaveProductDataEntity) =
            clickListener(SaveProductDataEntity)
    }


    class ViewHolder(private val binding: ItemListSaveProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val buttonDelete = binding.imageButton3
        val buttonCall = binding.call

        fun bind(data: SaveProductDataEntity) {
            binding.data = data
            binding.tvHarga.text = rupiah(data.harga)
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