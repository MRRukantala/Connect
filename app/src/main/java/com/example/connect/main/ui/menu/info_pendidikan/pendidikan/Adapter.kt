//package com.example.connect.main.ui.menu.info_pendidikan.pendidikan
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.connect.databinding.ItemListMyPendidikanBinding
//import com.example.connect.main.ui.menu.info_pendidikan.mysubPendidikan
//
//class Adapter(private val onClickListener: OnClickListener) : ListAdapter<mysubPendidikan, Adapter.ViewHolder>(DiffCallback) {
//    object DiffCallback : DiffUtil.ItemCallback<mysubPendidikan>(){
//        override fun areItemsTheSame(oldItem: mysubPendidikan, newItem: mysubPendidikan): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(
//            oldItem: mysubPendidikan,
//            newItem: mysubPendidikan
//        ): Boolean {
//            return oldItem.
//        }
//
//    }
//
//    class OnClickListener(val clickListener : (property : mysubPendidikan) -> Unit) {
//        fun onClick(property: mysubPendidikan) = clickListener(property)
//    }
//
//    class ViewHolder(private val binding: ItemListMyPendidikanBinding) :
//    RecyclerView.ViewHolder(binding.root){
//
//        fun bind(property : mysubPendidikan){
//            binding.item = property
//            binding.executePendingBindings()
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        return Adapter(
//            ItemListMyPendidikanBinding.inflate(
//                LayoutInflater.from(parent.context)
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val property = getItem(position)
//
//        holder.itemView.setOnClickListener{
//            onClickListener.onClick(property)
//        }
//
//        holder.bind(property)
//    }
//}