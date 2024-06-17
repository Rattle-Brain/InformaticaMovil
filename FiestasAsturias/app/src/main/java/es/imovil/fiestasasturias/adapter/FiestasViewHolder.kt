package es.imovil.fiestasasturias.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.databinding.FragmentFiestasBinding
import es.imovil.fiestasasturias.model.Fiesta

class FiestasViewHolder(val binding: FragmentFiestasBinding):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

    fun bind(f: Fiesta) {
        with(binding) {
            fNombre.text = f.nombre
            fMunicipio.text = f.municipio
            fLocalidad.text = f.municipio
            fDescCorta.text = f.descripcionCorta
        }

    }
}