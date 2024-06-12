package es.imovil.fiestasasturias.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import es.imovil.fiestasasturias.model.Fiesta
import es.imovil.fiestasasturias.databinding.FragmentFiestasBinding

class FiestasAdapter () : ListAdapter<Fiesta, FiestasViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiestasViewHolder {
        val itemViewBinding = FragmentFiestasBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FiestasViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: FiestasViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}