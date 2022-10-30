package com.example.connect.presentation.main.home.tablayout.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListAgendasBinding
import com.example.connect.domain.entity.AgendaEntity
import com.example.connect.utilites.enums.ConverterDate
import com.example.connect.utilites.ui.date.convertTimeToListNotif
import com.example.connect.utilites.ui.date.convertUTC2TimeTo2

class AgendaAdapter(val onclickListener: OnclickListener) :
    ListAdapter<AgendaEntity, AgendaAdapter.ViewHolder>(
        DiffCallback
    ) {

    inner class ViewHolder(
        private var binding: ItemListAgendasBinding,

        ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AgendaEntity) {
            binding.agenda = data
            binding.executePendingBindings()
            binding.textView12.text = data.tanggal.convertUTC2TimeTo2(ConverterDate.FULL_DATE)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<AgendaEntity>() {
        override fun areItemsTheSame(oldItem: AgendaEntity, newItem: AgendaEntity) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AgendaEntity, newItem: AgendaEntity) =
            oldItem.id == newItem.id
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListAgendasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agendaItem = getItem(position)
        holder.bind(agendaItem)
        holder.itemView.setOnClickListener {
            onclickListener.onClick(agendaItem)
        }
    }

    class OnclickListener(
        val clickListener: (entity: AgendaEntity) ->
        Unit
    ) {
        fun onClick(entity: AgendaEntity) = clickListener(entity)
    }
}