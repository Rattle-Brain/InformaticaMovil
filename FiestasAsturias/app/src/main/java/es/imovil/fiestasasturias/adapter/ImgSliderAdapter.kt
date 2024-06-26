package es.imovil.fiestasasturias.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.imovil.fiestasasturias.databinding.ImgSlideBinding

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