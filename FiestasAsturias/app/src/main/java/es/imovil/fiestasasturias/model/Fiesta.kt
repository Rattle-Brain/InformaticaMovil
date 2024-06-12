package es.imovil.fiestasasturias.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fiesta_table")
data class Fiesta (
@PrimaryKey val nombre: String,
    val zona: String,
    val tipo: String,
    val municipio: String,
    val localidad: String,
    val dias: String,
    val email: String,
    val web: String,
    val facebook: String,
    val twitter: String,
    val youtube: String,
    val pinterest: String,
    val instagram: String,
    val rss: String,
    val nombreCanal: String,
    val canalUrl: String,
    val descripcionCorta: String,
    val descripcion: String,
    val coordenadas: String,
    val slide: String,
    val slideTitulo: String,
    val slideUrl: String
    )
{
    companion object DIFF_CALLBACK: DiffUtil.ItemCallback<Fiesta>() {
        override fun areItemsTheSame(oldItem: Fiesta, newItem: Fiesta): Boolean {
            return oldItem.equals(newItem)
        }
        override fun areContentsTheSame(oldItem: Fiesta, newItem: Fiesta): Boolean {
            return oldItem == newItem
        }
    }
}