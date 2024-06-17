package es.imovil.fiestasasturias.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.databinding.FragmentFiestasBinding

class FiestasAdapter () : ListAdapter<Fiesta, FiestasViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiestasViewHolder {
        val itemViewBinding = FragmentFiestasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FiestasViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FiestasViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<Fiesta>() {
        override fun areItemsTheSame(oldItem: Fiesta, newItem: Fiesta): Boolean {
            return oldItem.nombre == newItem.nombre
        }
        override fun areContentsTheSame(oldItem: Fiesta, newItem: Fiesta): Boolean {
            return oldItem == newItem
        }
    }
}