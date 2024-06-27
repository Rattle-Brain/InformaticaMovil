package es.imovil.fiestasasturias.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.databinding.ImgSlideBinding

/**
 * Adapter para el slider de fotos en la pantalla de detalles de la fiesta
 */
class ImgSliderAdapter(private val slides: List<String>?,
                       private val title: String?
) : RecyclerView.Adapter<ImgSliderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgSliderViewHolder {
        val imgSliderBinding = ImgSlideBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImgSliderViewHolder(imgSliderBinding)
    }

    override fun getItemCount(): Int{
        return slides?.size ?: 0
    }

    override fun onBindViewHolder(holder: ImgSliderViewHolder, position: Int) {
        holder.bind(slides?.get(position) ?: "", title ?: "")
    }
}