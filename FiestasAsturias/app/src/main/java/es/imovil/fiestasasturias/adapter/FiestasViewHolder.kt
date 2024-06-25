package es.imovil.fiestasasturias.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.controller.ClickListenerController
import es.imovil.fiestasasturias.databinding.FiestaViewBinding
import es.imovil.fiestasasturias.model.Fiesta

class FiestasViewHolder(val binding: FiestaViewBinding, private val clickListener: ClickListenerController):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    override fun onClick(v: View?) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            clickListener.onItemClick(position)
        }
    }

    fun bind(f: Fiesta) {
        with(binding) {
            fNombre.text = f.nombre
            fMunicipio.text = f.municipio
            fLocalidad.text = f.municipio
            fDescCorta.text = f.descripcionCorta
        }

    }
    init {
        binding.root.setOnClickListener(this)
    }
}