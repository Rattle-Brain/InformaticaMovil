package es.imovil.fiestasasturias.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import es.imovil.fiestasasturias.databinding.ImgSlideBinding

/**
 * ViewHolder para las fotos de detalles
 */
class ImgSliderViewHolder(private val imgSlideBinding: ImgSlideBinding): RecyclerView.ViewHolder(imgSlideBinding.root) {

    fun bind(slide: String, title: String) {
        with(imgSlideBinding) {
            if(slide == ""){
                return
            }else {
                Picasso
                    .get()
                    .load("https://www.turismoasturias.es/${slide}")
                    .into(imageSlide)
            }
        }
    }
}