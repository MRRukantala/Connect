package com.example.connect.main.ui.home.tablayout.agenda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.connect.databinding.ItemListAgendasBinding
import com.example.connect.main.ui.home.tablayout.agenda.model.Agenda

class AgendaAdapter(
    private val onClickListener: OnClickListener,
) : ListAdapter<Agenda, AgendaAdapter.AgendaViewHolder>(DiffCallback) {
    object DiffCallback : DiffUtil.ItemCallback<Agenda>() {
        override fun areItemsTheSame(oldItem: Agenda, newItem: Agenda): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Agenda, newItem: Agenda): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class AgendaViewHolder(private var binding: ItemListAgendasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(agenda: Agenda) {
            binding.agenda = agenda
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AgendaViewHolder {
        return AgendaViewHolder(ItemListAgendasBinding.inflate(LayoutInflater.from(parent.context), null, false))
    }

    override fun onBindViewHolder(holder: AgendaViewHolder, position: Int) {
        val agendaProperty = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(agendaProperty)
        }

        holder.bind(agendaProperty)
    }

    class OnClickListener(val clickListener: (agendaProperty: Agenda) -> Unit) {
        fun onClick(agenda: Agenda) = clickListener(agenda)
    }

}