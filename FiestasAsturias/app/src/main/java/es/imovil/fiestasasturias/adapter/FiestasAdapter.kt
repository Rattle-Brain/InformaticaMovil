package es.imovil.fiestasasturias.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import es.imovil.fiestasasturias.controller.ClickListenerController
import es.imovil.fiestasasturias.databinding.FiestaViewBinding
import es.imovil.fiestasasturias.model.Fiesta

class FiestasAdapter (private val clickListener: ClickListenerController) : ListAdapter<Fiesta, FiestasViewHolder>(Fiesta.DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FiestasViewHolder {
        val itemViewBinding = FiestaViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FiestasViewHolder(itemViewBinding, clickListener)
    }

    override fun onBindViewHolder(holder: FiestasViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}