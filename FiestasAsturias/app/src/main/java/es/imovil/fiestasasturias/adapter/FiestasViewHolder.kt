package es.imovil.fiestasasturias.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.controller.ClickListenerController
import es.imovil.fiestasasturias.databinding.FiestaViewBinding
import es.imovil.fiestasasturias.model.Fiesta
import com.squareup.picasso.Picasso
import es.imovil.fiestasasturias.R

class FiestasViewHolder(val binding: FiestaViewBinding, private val clickListener: ClickListenerController):
    RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    override fun onClick(v: View?) {
        val position = layoutPosition
        if (position != RecyclerView.NO_POSITION) {
            clickListener.onItemClick(position)
        }
    }

    fun bind(f: Fiesta) {
        with(binding) {
            fNombre.text = f.nombre
            fMunicipio.text = f.municipio
            fLocalidad.text = f.localidad
            fDescCorta.text = f.descripcionCorta

            Picasso
                .get()
                .load("https://www.turismoasturias.es/${f.slide}")
                .centerCrop().resize(150,150)
                .placeholder(R.drawable.image_placeholder_thumbnail)
                .into(fThumbnail)
        }


    }
    init {
        binding.root.setOnClickListener(this)
    }
}